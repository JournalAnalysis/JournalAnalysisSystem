package com.example.demo.service;

import com.example.demo.util.MysqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@Slf4j
public class MysqlService {
    @Autowired
    MysqlUtil mysqlUtil;

    public void createTables(String logid) throws IOException, SQLException, ClassNotFoundException {
        String tmplt = readFile("./sqlTemplates/create logid_table.sql");
        tmplt = tmplt.replaceAll("logid",logid);
        String outpath = "./sqlTemplates/"+logid+"_table.sql";
        try{
            Files.write(Paths.get(outpath),tmplt.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        mysqlUtil.execCreateSql(outpath);
    }

    public void createViews(String logid) throws IOException, SQLException, ClassNotFoundException {
        String tmplt = readFile("./sqlTemplates/create logid_view.sql");
        tmplt = tmplt.replaceAll("logid",logid);
        String outpath = "./sqlTemplates/"+logid+"_view.sql";
        try{
            Files.write(Paths.get(outpath),tmplt.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        mysqlUtil.execCreateSql(outpath);
    }

    public void createGenTables() throws IOException, SQLException, ClassNotFoundException {
        String path = "./sqlTemplates/create general_table.sql";

        mysqlUtil.execCreateSql(path);
    }

    private String readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string = null;
        StringBuffer sb = new StringBuffer();
        while((string = br.readLine()) != null){
            sb.append(string+"\n");
        }
        br.close();
        return sb.toString();
    }

    private String GetConnection() {
        Connection comm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            comm = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "Book091212");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comm.toString();
    }

}
