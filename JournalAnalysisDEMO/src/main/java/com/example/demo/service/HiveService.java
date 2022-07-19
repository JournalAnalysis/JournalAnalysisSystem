package com.example.demo.service;

import com.example.demo.Repository.LogRepository;
import com.example.demo.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SqoopService sqoopService;
    @Autowired
    private MysqlService mysqlService;

    public List<String> hiveShowTables(String databaseName) throws SQLException, ClassNotFoundException {
        Class.forName(hiveDriver);
        Connection conn = DriverManager.getConnection(hiveUrl + databaseName, hiveUser, hivePwd);


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


    public List<String> hiveQueryAll(String databaseName, String tableName) throws ClassNotFoundException, SQLException {
        Class.forName(hiveDriver);
        Connection conn = DriverManager.getConnection(hiveUrl + databaseName, hiveUser, hivePwd);
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

    @Async
    public List<String> loadData(String inputPath, String logid, String uname) throws Exception {
        Class.forName(hiveDriver);
        Connection conn = DriverManager.getConnection(hiveUrl, hiveUser, hivePwd);
        Statement stmt = conn.createStatement();

//        String inputPath = "'/input/" + uname + "/" + logid + ".log'";
        String tableName = logid + "_content";
        String createSql = "CREATE TABLE " + tableName + " ( content STRING )";
        String sql = "LOAD DATA INPATH '" + inputPath + "' INTO TABLE " + tableName;

        System.out.println("Running: " + sql);
        stmt.execute(createSql);
        stmt.execute(sql);

        List<String> list = new ArrayList<>();
//        while (res.next()) {
////            System.out.println(res.getString(1));
//            list.add(res.getString(1));
//        }
        List<Log> currentLog = logRepository.findByLogid(logid);
        int complete = 0;
        int total = 21;
        //分析数据
        //1、建表
        System.out.println("上传文件到hadoop");
        String[] createSqlArr = createTables(logid);
        for (int i = 0; i < createSqlArr.length - 1; i++) {
            System.out.println(createSqlArr[i]);
            stmt.execute(createSqlArr[i]);
            complete++;
            if (currentLog.isEmpty()) {
                currentLog = logRepository.findByLogid(logid);
            } else {
                currentLog.get(0).setLogstate(complete * 1.0 / total * 100 + "%");
                logRepository.save(currentLog.get(0));
            }

        }

        //2、向表内插入数据
        System.out.println("hive加载并分析数据");
        String[] insertSqlArr = analysisData(logid);
        for (int i = 0; i < insertSqlArr.length - 1; i++) {
            stmt.execute(insertSqlArr[i]);
            System.out.println(insertSqlArr[i]);
            complete++;
            if (currentLog.isEmpty()) {
                currentLog = logRepository.findByLogid(logid);
            } else {
                System.out.println(complete * 1.0 / total * 100);
                currentLog.get(0).setLogstate(complete * 1.0 / total * 100+"");
                logRepository.save(currentLog.get(0));
            }
        }

        //3、建立mysql表用来接收导出数据
        System.out.println("建立MySQL接收数据表");
        mysqlService.createTables(logid);
        mysqlService.createViews(logid);
        complete++;
        if (currentLog.isEmpty()) {
            currentLog = logRepository.findByLogid(logid);
        } else {
            currentLog.get(0).setLogstate(complete * 1.0 / total * 100 + "%");
            logRepository.save(currentLog.get(0));
        }

        //4、使用sqoop导出数据
        System.out.println("sqoop导出数据");
        String[] exportTableArr = {"", "_first", "_url_top", "_ip_black"};
        String hadoopExtDir = "/user/hive/warehouse/janal.db/";
        for (int i = 0; i < exportTableArr.length; i++) {
            System.out.println("第" + i + "张表");
            sqoopService.hdfsToMysql(hadoopExtDir + logid + exportTableArr[i], logid + exportTableArr[i]);
            complete++;
            if (currentLog.isEmpty()) {
                currentLog = logRepository.findByLogid(logid);
            } else {
                currentLog.get(0).setLogstate(complete * 1.0 / total * 100 + "%");
                logRepository.save(currentLog.get(0));
            }
        }
        if (currentLog.isEmpty()) {
            currentLog = logRepository.findByLogid(logid);
        } else {

            currentLog.get(0).setLogstate("finish");
            logRepository.save(currentLog.get(0));
        }
        stmt.close();
        conn.close();
        return list;
    }

    public String[] createTables(String logid) throws IOException {
        String tmplt = readFile("./sqlTemplates/create hive logid_table.sql");
        tmplt = tmplt.replaceAll("logid", logid);
        String[] sqlList = tmplt.split(";");

        return sqlList;
    }

    public String[] analysisData(String logid) throws IOException {
        String tmplt = readFile("./sqlTemplates/insert hive logid_data.sql");
        tmplt = tmplt.replaceAll("logid", logid);
        String[] sqlList = tmplt.split(";");

        return sqlList;
    }

    private String readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string = null;
        StringBuffer sb = new StringBuffer();
        while ((string = br.readLine()) != null) {
            sb.append(string + "\n");
        }
        br.close();
        return sb.toString();

    }
}
