package com.example.demo.controller;

import com.example.demo.service.MysqlService;
import com.example.demo.util.MysqlUtil;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class MysqlController {
    @Autowired
    private MysqlService mysqlService;

    @RequestMapping("/mysqlTest")
    public String mysqlTest() throws SQLException, IOException, ClassNotFoundException {
        String logid = "testlogid";
        mysqlService.createTables(logid);
        mysqlService.createViews(logid);
        System.out.println("testlogid");
        return logid;
    }
}
