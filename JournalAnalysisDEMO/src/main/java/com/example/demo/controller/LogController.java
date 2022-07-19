package com.example.demo.controller;

import java.sql.*;

import com.example.demo.Repository.LogRepository;
import com.example.demo.bean.GroupBean;
import com.example.demo.entity.Log;
import com.example.demo.service.LogService;
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

    private Connection connection = null;

    PreparedStatement ps=null;
    ResultSet rs=null;
    ResultSetMetaData m=null;

    @GetMapping("/getAll")
    public List<Log> getAll(){
        return logRepository.findAll();
    }

    private String GetConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection.toString();}

    @PostMapping("/testRecent")
    public Log testRecent(@RequestBody Log log){
        return log;
    }
    @PostMapping("/getRecent")
    public List<Log> getRecent(@RequestBody Log log){
        //return log;
        return logRepository.findByUnameAndUptime(log.getUname(),log.getLoginf(),log.getUptime());
    }

    @PostMapping("/getHistory")
    public List<Log> getHistory(@RequestBody Log log){
//        if(log.getLogname().isEmpty()){
//            return logRepository.findByUname(log.getUname());
//        }else{
           return logRepository.findByUnameAndLognameAndLogstate(log.getUname(),log.getLogname());
            //return logRepository.findByLogname(log.getLogname());
//        }
    }

    @PostMapping("/testCompany")
    public Log testCompany(@RequestBody Log log){
//        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
//        Date d = new Date();
//        log.setLogid(d.toString());
        return log;
    }
    @PostMapping("/getCompany")
    public List<Log> getCompany(@RequestBody Log log){
        if(log.getUptime()==null){
            return logRepository.findByCnameAndUnameAndLogauthAndLognameAndLogstate(log.getCname(),log.getUname(),log.getLogauth(),log.getLogname());
        }else{
            return logRepository.findByCnameAndUnameAndUptimeAndLogauthAndLognameAndLogstate(log.getCname(),log.getUname(),log.getLogauth(),log.getLogname(),log.getUptime(),log.getLoginf());
        }
    }

    @PostMapping("/testid")
    public String TestId(@RequestBody String logid){
        return logid;
    }


        @PostMapping("/area")
    public RandomAccess Area (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        this.GetConnection();

        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT city as name,number as value FROM web_test." + logid + "_city";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }

    @PostMapping("/browser")
    public RandomAccess Browser (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT browser as name,number as value FROM web_test." + logid + "_browser";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }

    @PostMapping("/source")
    public RandomAccess Source (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT ref_type as name,number as value FROM web_test." + logid + "_ref";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }

    @PostMapping("/time")
    public RandomAccess Time (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT ref_type as name,number as value FROM web_test." + logid + "_time";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }

    @PostMapping("/isp")
    public RandomAccess Isp (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT isp as name,number as value FROM web_test." + logid + "_isp";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }

    @PostMapping("/client")
    public RandomAccess Client (@RequestBody String logid) throws SQLException {
        Connection connection = null;

        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData m=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT client as name,number as value FROM web_test." + logid + "_client";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            m = rs.getMetaData();
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                ArrayList _list = new ArrayList();

                for (int i = 1; i <= len; i++) {

                    _list.add(rs.getString(i));

                }

                list.add(_list);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }
}

