package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class HiveService {
    @Value("${hive.driver-class-name}")
    private String hiveDriver;
    @Value("${hive.url}")
    private String hiveUrl;
    @Value("${hive.user}")
    private String hiveUser;
    @Value("${hive.password}")
    private String hivePwd;

    public List<String> hiveShowTables(String databaseName) throws SQLException, ClassNotFoundException {
        Class.forName(hiveDriver);
        Connection conn = DriverManager.getConnection(hiveUrl+databaseName, hiveUser, hivePwd);

        Statement stmt = conn.createStatement();
        // 显示所有表
        String sql = "show tables";
        //显示表内所有信息
//        String sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        List<String> list = new ArrayList<>();

        while (res.next()) {
            System.out.println(res.getString(1));
            list.add(res.getString(1));
        }

        stmt.close();
        conn.close();
        return list;

    }

    public List<String> hiveQueryAll(String databaseName,String tableName) throws ClassNotFoundException, SQLException {
        Class.forName(hiveDriver);
        Connection conn = DriverManager.getConnection(hiveUrl+databaseName, hiveUser, hivePwd);

        Statement stmt = conn.createStatement();
        //显示表内所有信息
        String sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);

        List<String> list = new ArrayList<>();
        int count = res.getMetaData().getColumnCount();

        while (res.next()) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < count; i++) {
                sb.append(res.getString(i) + " ");
            }
            sb.append(res.getString(count));
            list.add(sb.toString());
            System.out.println(sb);
        }


        stmt.close();
        conn.close();
        return list;

    }
}
