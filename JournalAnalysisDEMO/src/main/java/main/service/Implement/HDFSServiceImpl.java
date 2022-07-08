<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/service/Implement/HDFSServiceImpl.java
package com.janal.demo.service.Implement;
========
package main.service.Implement;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/service/Implement/HDFSServiceImpl.java

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/service/Implement/HDFSServiceImpl.java
import com.janal.demo.service.HDFSService;
========
import main.service.HDFSService;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/service/Implement/HDFSServiceImpl.java

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 空字符代表路径待定
 */

@Slf4j
@Service
public class HDFSServiceImpl implements HDFSService {
    @Autowired
    private static FileSystem fileSystem;

    @Override
    public boolean createFile(String path, MultipartFile file) {
        /**
         * author:zhangxiangyu
         * 用户上传文件后获取文件
        */
        boolean target=false;
        if(existFile(path)){
            return false;
        }
        String fileName=file.getName();
        Path newPath=new Path(path+"/"+fileName);
        FSDataOutputStream outputStream = null;
        try{
           outputStream=fileSystem.create(newPath);
           outputStream.write(file.getBytes());
           target=true;
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        finally {
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
        if(path.equals("")||path==null){
        return false;
        }
        Path src=new Path(path);
        try {
            return fileSystem.exists(src);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFile(String path) {
        boolean target = false;
        if (path==null||path.equals("")) {
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
    public static Workbook readXls(String filePath){
        if(filePath.isEmpty()){
            return null;
        }
        try{
            InputStream inputStream=new FileInputStream(filePath);
            if(filePath.contains(".xls")){
                return new HSSFWorkbook(inputStream);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //将用户上传的文件输出到hdfs中
    public static void readContent(String path){
        try{
            Workbook wb=readXls(path);
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                Sheet sheet= wb.getSheetAt(numSheet);
                if(sheet==null){
                    continue;
                }
                for (int numRow = 0; numRow <=sheet.getLastRowNum() ; numRow++) {
                    Row row=sheet.getRow(numRow);
                    Cell cell=row.getCell(0);
                    String cellResult= cell.getStringCellValue();
                    Path filePath=new Path("/root/server/input/a.log");
                    FSDataOutputStream fsDataOutputStream=fileSystem.create(filePath);
                    fsDataOutputStream.write(cellResult.getBytes());
                    fsDataOutputStream.close();

                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void readCSV() throws IOException{

    }
}
