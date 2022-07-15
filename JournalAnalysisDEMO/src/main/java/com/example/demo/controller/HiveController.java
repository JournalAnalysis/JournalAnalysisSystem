package com.example.demo.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 DataSource 操作 Hive
 */
@RestController
@RequestMapping("/hive")
public class HiveController {
    @Autowired
    private HiveService hiveService;

    @RequestMapping("/showTables")
    public List<String> hiveShowTables() throws SQLException, ClassNotFoundException {
        List<String> list = hiveService.hiveShowTables("test");
        return list;
    }

    @RequestMapping("/queryTable")
    public List<String> hiveQueryTable() throws SQLException, ClassNotFoundException {
        List<String> list = hiveService.hiveQueryAll("test","emp");
        return list;
    }

}