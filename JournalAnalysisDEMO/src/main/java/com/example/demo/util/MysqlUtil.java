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
}
