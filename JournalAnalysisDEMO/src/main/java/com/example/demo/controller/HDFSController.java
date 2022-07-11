package com.example.demo.controller;

import com.example.demo.service.HDFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
//import com.example.demo.service.HDFSService;

@Controller
public class HDFSController {

    @Autowired
    public HDFSService hdfsService;


    @PostMapping(value="/upload")
    public void getFile(MultipartFile file){
    String path="hdfs://input";

    }
}
