package com.example.demo.service.Implement;

import com.example.demo.service.HDFSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 空字符代表路径待定
 */

@Slf4j
@Service
public class HDFSServiceImpl implements HDFSService {
    @Autowired
    private FileSystem fileSystem;

    @Override
    public boolean createFile(String path, MultipartFile file) {
        /*
          用户上传文件后获取文件
         */
        boolean target = false;
//        if (existFile(path)) {
//            return false;
//        }
        String fileName = file.getOriginalFilename();
        Path newPath = new Path(path + "/" + fileName);
        FSDataOutputStream outputStream = null;
        try {
            outputStream = fileSystem.create(newPath);
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
            target = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return target;
    }

    @Override
    public boolean existFile(String path) {
        if (path.equals("")) {
            return false;
        }
        Path src = new Path(path);
        try {
            return fileSystem.exists(src);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFile(String path) {
        boolean target = false;
        if (path == null || path.equals("")) {
            return false;
        }
        if (!existFile(path)) {
            return false;
        }
        Path src = new Path(path);
        try {
            target = fileSystem.deleteOnExit(src);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return target;
    }

}
