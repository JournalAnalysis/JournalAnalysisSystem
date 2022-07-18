package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface HDFSService {

    public boolean createFile(String path, MultipartFile file,String fileName);


    public boolean existFile(String path);

    public boolean deleteFile(String path);
}
