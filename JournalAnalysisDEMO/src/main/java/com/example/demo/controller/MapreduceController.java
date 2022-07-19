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
    public void csvMap(String uname){
        try{
            String inputPath="hdfs://120.55.45.150:8020/input/"+uname;
            String outputPath="hdfs://120.55.45.150:8020/output/"+uname;
            List<String> inputs=service.listFile(inputPath);
            List<String> outputs=service.listFile(outputPath);
            String finalInputPath = inputPath;
            AtomicReference<String> finalOutputPath = new AtomicReference<>("");
            inputs.forEach(item->{
                if(!outputs.contains(item)){
                    try {
                        if(item.contains(".csv")){
                            finalOutputPath.set(outputPath + "/" + item);
                            mapreduceService.cleanCSV(item, finalInputPath, finalOutputPath.get());
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
            }
            );
        }
        catch (Exception e){
            log.error(e.getMessage());

        }
    }

}
