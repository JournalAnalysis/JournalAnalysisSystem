package com.example.demo.service.Implement;

import com.example.demo.service.HDFSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 空字符代表路径待定
 */

@Slf4j
@Service
public class HDFSServiceImpl implements HDFSService {
    @Autowired
    private FileSystem fileSystem;

    @Override

    public boolean createFile(String inputPath,String outputPath, MultipartFile file,String fileName) {

        /**
         * author:zhangxiangyu
         * 用户上传文件后获取文件
         */
        Path newPath;
        boolean target = false;
//        if (existFile(path)) {
//            return false;
//        }
        if(file.getOriginalFilename().contains(".csv")||file.getOriginalFilename().contains(".xls")||file.getOriginalFilename().contains("xlsx")){
            newPath=new Path(inputPath+"/"+fileName);
        }else{
            newPath = new Path(outputPath + "/" + fileName);
        }

        FSDataOutputStream outputStream = null;
        try {
            outputStream = fileSystem.create(newPath,true);
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
            target = fileSystem.delete(src,true);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return target;
    }
    @Override
    public List<String> listFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return Collections.emptyList();
        }
        if (!existFile(path)) {
            return Collections.emptyList();
        }
        List<String> resultList = new ArrayList<>();

        Path src = new Path(path);
        try {
            RemoteIterator<LocatedFileStatus> fileIterator = fileSystem.listFiles(src, true);
            while (fileIterator.hasNext()) {
                LocatedFileStatus next = fileIterator.next();
                Path filePath = next.getPath();
                String fileName = filePath.getName();
                resultList.add(fileName);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return resultList;
    }

}
