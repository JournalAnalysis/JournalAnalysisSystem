package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface HDFSService {

    public boolean createFile(String inputPath, String outputPath,MultipartFile file,String fileName);


    public boolean existFile(String path);

    public boolean deleteFile(String path);
    public List<String> listFile(String path);
}
