package com.example.demo.controller;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.LogRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Company;
import com.example.demo.entity.Log;
import com.example.demo.entity.UploadFileResponse;
import com.example.demo.entity.User;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FileService fileService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    ResourceLoader resourceLoader;


    @PostMapping("/getStaff")
    public List<User> GetStaff(@RequestBody User user){

        List<User> Users = userRepository.findByCnameAndUtypeAndUname(user.getCname(), user.getUtype(), user.getUname());
        return Users;
    }

    @PostMapping("/changeAuth")
    public String changeAuth(@RequestBody User user){
        List<User> users = userRepository.findByUname(user.getUname());
        users.get(0).setUauth(user.getUauth());
        userRepository.save(users.get(0));
        return "修改成功";
    }

    @PostMapping("/inf")
    public List<User> Inf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        return Users;
    }

    @PostMapping("/adminf")
    public List<Company> AdmInf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        String cname = Users.get(0).getCname();
        List<Company> companies = companyRepository.findByCname(cname);
        return companies;
    }

    @PostMapping("/changeinf")
    public String ChangeInf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        User user2 = Users.get(0);
        user2.setUpassword(user.getUpassword());
        List<Company> companies = companyRepository.findByCcode(user.getCcode());
        if(!companies.isEmpty()){
            user2.setUauth("low");
            user2.setCname(companies.get(0).getCname());
            user2.setUtype("staff");
        }
        userRepository.save(user2);
        return "修改成功！";
    }

    @RequestMapping("/upload/file")
    public UploadFileResponse uploadFile(
            @RequestParam("file") MultipartFile file,@RequestParam("uname") String uname){

        String fileName = fileService.storeFile(file,uname);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/downloadFile/")
                .path(fileName)
                .toUriString();
        //获取上传用户名
        System.out.println(uname);
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @RequestMapping("/upload/log")
    public String uploadLog(@RequestBody Log log){

        String path = System.getProperty("user.dir")+"/uploads/"+log.getLogid();
        log.setLoglocation(path);
        log.setLogstate("underway");
        logRepository.save(log);
        return "上传成功！";

    }

    @RequestMapping("/upload/address")
    public String uploadLink(@RequestBody Log log){
        int max=10000;
        int min=1000;
        Random random = new Random();
        String code = String.valueOf(random.nextInt(max) % (max - min + 1) + min);
        String logid = code + log.getLogname();
        log.setLogid(logid);
        log.setLogstate("underway");
        logRepository.save(log);
        return "上传成功！";
    }

    //下载日志文件
    @RequestMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {

        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}