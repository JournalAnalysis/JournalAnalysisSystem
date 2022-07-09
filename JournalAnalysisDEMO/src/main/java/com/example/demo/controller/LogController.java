package com.example.demo.controller;

import com.example.demo.Repository.LogRepository;
import com.example.demo.bean.GroupBean;
import com.example.demo.entity.Log;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @Autowired
    LogRepository logRepository;

    @GetMapping("/getAll")
    public List<Log> getAll(){
        return logRepository.findAll();
    }

    @PostMapping("/area")
    public String getArea(@RequestBody String logid){
        return logid;
    }


//    @GetMapping("/getRecent")
//    public List<Log> getRecent{
//        return logRepository.findByUname()
//    }
}
