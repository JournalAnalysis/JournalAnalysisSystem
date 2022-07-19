package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.taskdefs.SQLExec.DelimiterType;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MysqlUtil {
    @Value("${spring.datasource.url}")
    private String mysqlUrl;
    @Value("${spring.datasource.username}")
    private String name;
    @Value("${spring.datasource.password}")
    private String pwd;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    public void execCreateSql(String sqlpath) throws SQLException, ClassNotFoundException, IOException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(mysqlUrl, name, pwd);

        File sqlFile = new File(sqlpath);
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        Resources.setCharset(Charset.forName("UTF-8"));
        scriptRunner.setLogWriter(null);
        scriptRunner.setSendFullScript(true);
        Reader read = new FileReader(sqlFile);
        scriptRunner.runScript(read);

        read.close();
        sqlFile.delete();
    }

    public ArrayList execSelectSql(String sql) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(mysqlUrl, name, pwd);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData m = null;
        ArrayList list = new ArrayList();
        try {
//            String sql = "SELECT city as name,number as value FROM web_test." + logid + "_city";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs.close();
        connection.close();
        return list;
    }
}
