package com.example.demo.controller;

import com.example.demo.service.HDFSService;
import com.example.demo.service.XlsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String xlsResult(){
        try{
            String inputPath="/input";
            String outputPath="/output";
            List<String> inputs=hdfsService.listFile(inputPath);
            List<String> outputs=hdfsService.listFile(outputPath);
            inputs.forEach(item->{
                        if(!outputs.contains(item)){
                            if(item.contains(".xls")||item.contains(".xlsx")){
                                try {
                                    xlsService.readXlsContent(outputPath,inputPath,item);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println(item);
                            }
                        }
                    }
            );
            return "OK";
        }
        catch (Exception e){
            log.error(e.getMessage());

        }
        return "done";
    }
}
