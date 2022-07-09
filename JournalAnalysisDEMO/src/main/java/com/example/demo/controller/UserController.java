package com.example.demo.controller;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Company;
import com.example.demo.entity.UploadFileResponse;
import com.example.demo.entity.User;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FileService fileService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/inf")
    public List<User> Inf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        return Users;
    }

    @PostMapping("/changeinf")
    public String ChangeInf(@RequestBody User user){
        List<User> Users = userRepository.findByUname(user.getUname());
        User user2 = Users.get(0);
        user2.setPassword(user.getPassword());
        List<Company> companies = companyRepository.findByCcode(user.getCcode());
        user2.setCname(companies.get(0).getCname());
        userRepository.save(user2);
        return "修改成功！";
    }

    //上传单个文件
    @RequestMapping("/upload/file")
    public UploadFileResponse uploadFile(
            @RequestParam("file") MultipartFile file){

        String fileName = fileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

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
