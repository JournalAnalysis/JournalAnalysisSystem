//package com.example.demo.service;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ViewsService {
//
//    private String GetConnection() {
//        Connection comm = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            comm = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "Book091212");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return comm.toString();
//    }
//
//}
