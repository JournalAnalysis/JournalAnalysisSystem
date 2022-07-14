//package com.example.demo.controller;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.sqoop.Sqoop;
//import org.apache.sqoop.tool.ExportTool;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SqoopTest {
//    public static void main(String[] args) {
//        List generatedJarsList = new ArrayList();
//        Configuration conf = new Configuration();
//        conf.set("fs.default.name", "hdfs://master:9000");
//        conf.set("hadoop.job.ugi", "hadooper,hadoopgroup");
//        conf.set("mapred.job.tracker", "master:9001");
//        ArrayList list = new ArrayList();  //定义一个list
//        list.add("--table");
//        list.add("a_baat_client");
//        // mysql中的表。将来数据要导入到这个表中。
//        list.add("--export-dir");
//        list.add("/tmp/datathree/");
//        //hdfs上的目录。这个目录下的数据要导入到a_baat_client这个表中。
//        list.add("--connect");
//        list.add("jdbc:mysql://192.168.1.10:3306/report");
//        //mysql的链接
//        list.add("--username");
//        list.add("root");
//        //mysql的用户名
//        list.add("--password");
//        list.add("root");
////         mysql的密码
//        list.add("--lines-terminated-by");
//        list.add("\\n");
//        //数据的换行符号
//        list.add("-m");
//        list.add("1");
////         定义mapreduce的数量。
//        String[] arg = new String[1];
//        ExportTool exporter = new ExportTool();
//        Sqoop sqoop = new Sqoop(exporter);
//        sqoop.setConf(conf);
//        arg = (String[]) list.toArray();
//        int result = Sqoop.runSqoop(sqoop, arg);
//        System.out.println("res:" + result);  //打印执行结果。
//    }
//}
