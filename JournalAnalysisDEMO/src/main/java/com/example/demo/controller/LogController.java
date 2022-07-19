package com.example.demo.controller;

import java.sql.*;

import com.example.demo.Repository.LogRepository;
import com.example.demo.bean.GroupBean;
import com.example.demo.entity.Log;
import com.example.demo.service.LogService;
import com.example.demo.util.MysqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @Autowired
    LogRepository logRepository;

    @Autowired
    MysqlUtil mysqlUtil;

    private Connection connection = null;

    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData m = null;

    @GetMapping("/getAll")
    public List<Log> getAll() {
        return logRepository.findAll();
    }

    @PostMapping("/testRecent")
    public Log testRecent(@RequestBody Log log) {
        return log;
    }

    @PostMapping("/getRecent")
    public List<Log> getRecent(@RequestBody Log log) {
        //return log;
        return logRepository.findByUnameAndUptime(log.getUname(), log.getLoginf(), log.getUptime());
    }

    @PostMapping("/getHistory")
    public List<Log> getHistory(@RequestBody Log log) {
//        if(log.getLogname().isEmpty()){
//            return logRepository.findByUname(log.getUname());
//        }else{
        return logRepository.findByUnameAndLognameAndLogstate(log.getUname(), log.getLogname());
        //return logRepository.findByLogname(log.getLogname());
//        }
    }

    @PostMapping("/testCompany")
    public Log testCompany(@RequestBody Log log) {
//        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
//        Date d = new Date();
//        log.setLogid(d.toString());
        return log;
    }

    @PostMapping("/getCompany")
    public List<Log> getCompany(@RequestBody Log log) {
        if (log.getUptime() == null) {
            return logRepository.findByCnameAndUnameAndLogauthAndLognameAndLogstate(log.getCname(), log.getUname(), log.getLogauth(), log.getLogname());
        } else {
            return logRepository.findByCnameAndUnameAndUptimeAndLogauthAndLognameAndLogstate(log.getCname(), log.getUname(), log.getLogauth(), log.getLogname(), log.getUptime(), log.getLoginf());
        }
    }

    @PostMapping("/testid")
    public String TestId(@RequestBody String logid) {
        return logid;
    }


    @PostMapping("/area")
    public RandomAccess Area(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT province as name,count as value FROM web_test." + logid + "_prov";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/topN")
    public RandomAccess TopN(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT url as name,value as value FROM web_test." + logid + "_top10";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/browser")
    public RandomAccess Browser(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT client_browser as name,count as value FROM web_test." + logid + "_brow";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/source")
    public RandomAccess Source(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ref_type as name,count as value FROM web_test." + logid + "_ref_type";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/time")
    public RandomAccess Time(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT access_hour as name,count as value FROM web_test." + logid + "_access_time";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/isp")
    public RandomAccess Isp(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT isp as name,count as value FROM web_test." + logid + "_isp";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/client")
    public RandomAccess Client(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT client_type as name,count as value FROM web_test." + logid + "_os";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }

    @PostMapping("/topN")
    public RandomAccess topN(@RequestBody String logid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT url as name,times as value FROM web_test." + logid + "_top10";
        ArrayList list = mysqlUtil.execSelectSql(sql);
        return list;
    }
}

