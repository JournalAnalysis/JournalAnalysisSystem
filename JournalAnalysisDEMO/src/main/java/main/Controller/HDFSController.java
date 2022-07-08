<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/Controller/HDFSController.java
package com.janal.demo.Controller;
========
package main.Controller;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/Controller/HDFSController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/Controller/HDFSController.java
import com.janal.demo.service.HDFSService;
========
import main.service.HDFSService;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/Controller/HDFSController.java

@Controller
public class HDFSController {
    @Autowired
    public HDFSService hdfsService;


    @PostMapping(value="/upload")
    public void getFile(MultipartFile file){
    String path="hdfs://input";

    }
}
