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

    @PostMapping("/getRecent")
    public List<Log> getRecent(){
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
        return connection.toString();
    }

    @PostMapping("/testid")
    public String TestId(@RequestBody String logid){
        return logid;
    }

    @PostMapping("/area")
    public RandomAccess Area (@RequestBody String logid) throws SQLException {
        this.GetConnection();
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
        
        return list;
    }

    @PostMapping("/browser")
    public RandomAccess Browser (@RequestBody String logid) throws SQLException {
        this.GetConnection();
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

        return list;
    }

}
