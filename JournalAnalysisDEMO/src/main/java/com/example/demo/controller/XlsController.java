package com.example.demo.controller;

import com.example.demo.service.HDFSService;
import com.example.demo.service.XlsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
public class XlsController {
    @Autowired
    private XlsService xlsService;

    @Qualifier("HDFSServiceImpl")
    @Autowired
    private HDFSService hdfsService;

    @RequestMapping("/xlsResult")
    @ResponseBody
    public String xlsResult(String uname){

        try{
            String inputPath="hdfs://120.55.45.150:8020/input/"+uname;
            String outputPath="hdfs://120.55.45.150:8020/output/"+uname;
            List<String> inputs=hdfsService.listFile(inputPath);
            List<String> outputs=hdfsService.listFile(outputPath);
            inputs.forEach(item-> outputs.forEach(
                    i->{
                        if(i.substring(0,i.lastIndexOf("."))!=item.substring(0, i.lastIndexOf("."))){
                          if(item.contains(".xls")||item.contains("xlsx")){
                             try{
                                 xlsService.readXlsContent(outputPath,inputPath,item);
                                 System.out.println("Ok");
                             }
                             catch (IOException e) {
                                 throw new RuntimeException(e);
                             }
                             System.out.println(item);
                          }
                        }
                    }
            )
            );
            return "OK";
        }
        catch (Exception e){
            log.error(e.getMessage());

        }
        return "done";
    }
}
