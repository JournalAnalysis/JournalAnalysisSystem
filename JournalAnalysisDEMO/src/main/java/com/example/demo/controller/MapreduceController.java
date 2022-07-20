package com.example.demo.controller;

import com.example.demo.service.HDFSService;
import com.example.demo.service.MapreduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Controller
public class MapreduceController {
    @Autowired
    private MapreduceService mapreduceService;
    @Qualifier("HDFSServiceImpl")
    @Autowired
    private HDFSService service;

    @RequestMapping("/CsvMap")
    @ResponseBody
    public void csvMap(String uname) {
        try {
            String inputPath = "hdfs://120.55.45.150:8020/input/" + uname;
            String outputPath = "hdfs://120.55.45.150:8020/output/" + uname;
            List<String> inputs = service.listFile(inputPath);
            System.out.println(Arrays.toString(inputs.toArray()));
            List<String> outputs = service.listFile(outputPath);
            System.out.println(Arrays.toString(outputs.toArray()));
            AtomicReference<String> finalInputPath = new AtomicReference<>("");
            AtomicReference<String> finalOutputPath = new AtomicReference<>("");
            inputs.forEach(item -> outputs.forEach(i -> {
                        if (i.substring(0, i.lastIndexOf(".")) != item.substring(0, i.lastIndexOf("."))) {
                            try {
                                if (item.contains(".csv")) {
                                    finalOutputPath.set(outputPath + "/" + item.replace("csv", "log"));
                                    finalInputPath.set(inputPath + "/" + item);
                                    mapreduceService.cleanCSV(item.substring(0, item.lastIndexOf(".")), finalInputPath.get(), finalOutputPath.get());
                                    System.out.println(item);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else{
                            log.error("FileAlreadyExists!");

                        }
                    }
            )
            );
        }
        catch (Exception e){
            log.error(e.getMessage());
        }


    }
}


