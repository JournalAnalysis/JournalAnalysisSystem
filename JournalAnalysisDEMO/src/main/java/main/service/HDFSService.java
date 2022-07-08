<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/service/HDFSService.java
package com.janal.demo.service;
========
package main.service;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/service/HDFSService.java

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface HDFSService {
public boolean createFile(String path,MultipartFile file);
public boolean existFile(String path);
public boolean deleteFile(String path);
}
