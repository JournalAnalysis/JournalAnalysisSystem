package com.example.demo.service;

import com.example.demo.Repository.FileException;
import com.example.demo.Repository.FileProperties;
import com.example.demo.controller.MapreduceController;
import com.example.demo.controller.XlsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Random;

@Service
public class FileService {
    public static String code;
    public static Random random;

    private final Path fileStorageLocation; // 文件在本地存储的地址
    @Qualifier("HDFSServiceImpl")
    @Autowired
    public HDFSService hdfsService;
    @Autowired
    public HiveService hiveService;
    @Autowired
    public XlsController xlsController;

    @Autowired
    public MapreduceController mapreduceController;


    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file, String uname) {
        int max=100000;
        int min=10000;
        random=new Random();
        code = String.valueOf(random.nextInt(max)%(max-min+1)+min);
        // Normalize file name

//        String fileName = code+ StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        String fileOrgName = file.getOriginalFilename();
        assert fileOrgName != null;
        String fileName = "log" + code+fileOrgName.substring(fileOrgName.lastIndexOf("."));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            //upload to hadoop

            String hadoopInputDir = "/input/" + uname;
            String hadoopOutputDir="/output/"+uname;
            hdfsService.createFile(hadoopInputDir,hadoopOutputDir,file, fileName);
            if(fileOrgName.contains("xls")||fileOrgName.contains("xlsx")){
                xlsController.xlsResult(uname);
                if(fileName.equals("xlsx")){
                 fileName=fileName.replace(".xlsx",".log");
                }
                else{
                    fileName=fileName.replace(".xls",".log");
                }
            }
            if(fileOrgName.contains("csv")){
                mapreduceController.csvMap(uname);
                fileName=fileName.replace(".csv",".log");
            }
            //load data to hive

            String hadoopFilePath = hadoopOutputDir + "/" + fileName;
            String logid = fileName.substring(0,fileName.lastIndexOf("."));
            hiveService.loadData(hadoopFilePath,logid,uname);


            return fileName.substring(0,fileName.lastIndexOf("."));
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
