package com.example.demo.service.Implement;

import com.example.demo.bean.SqoopBean;
import com.example.demo.service.SqoopService;
import com.example.demo.util.OperateLinuxCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SqoopServiceImpl implements SqoopService {
    @Value("${spring.datasource.url}")
    private String connect;//mysql连接
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${hdfs.hdfsPath}")
    private String hdfsAddr;
    @Value("${CentOS.ip}")
    private String ip;
    @Value("${CentOS.path.sqoop}")
    private String sqoopPath;
    @Override
    public SqoopBean mysqlTohdfs(String connect, String driver, String username, String password, String table, int m, String targetdir, String hdfsAddr) throws Exception {
        return null;
    }

    @Override
    public SqoopBean mysql2Hbase(String jdbc, String driver, String username, String password, String mysqlTable, String hbaseTableName, String columnFamily, String rowkey, int m) throws Exception {
        return null;
    }

    @Override
    public String hdfsToMysql(String srcdir, String table) throws Exception {
//        String sshIp = "192.168.80.130";
//        String sshUserName = "root";
//        String sshUserPwd = "root";
        Boolean result = OperateLinuxCommand.login(ip, username, password);
//        String cmd = "/opt/server/sqoop-1.4.7.bin__hadoop-2.6.0/bin/sqoop export
//        --connect \"jdbc:mysql://192.168.146.1:3306/janal?useUnicode=true&characterEncoding=utf-8\"
//        --username root --password root --table logid_url_top
//        --export-dir /user/hive/warehouse/access_log_url_top
//        --input-fields-terminated-by '\\001'"; //操作shell脚本（可以把多个明林感谢在一个脚本中批量执行）

        String cmdHead = sqoopPath+"/sqoop export ";
        String[] paras = new String[]{

                "--connect","\""+ connect+"\"",
                "--username", username,
                "--password",password,
                "--table", table,
                "--export-dir", srcdir,
                "--input-fields-terminated-by", "'\001'"
        };
        String cmdBody =parasToString(paras);
        String cmd = cmdHead + cmdBody;
        String info = "";
        if(result) {
            long begin = System.currentTimeMillis();
            info = OperateLinuxCommand.execute(cmd);
            long end = (System.currentTimeMillis() - begin) / 1000;
            System.out.println(info);
            System.out.println("--远程操作结束，共用时:{"+end+"}秒--");
        }
        return info;
    }

    private static String parasToString(String[] paras){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < paras.length; i+=2) {
            sb.append(paras[i]+" "+paras[i+1]+" ");
        }
        return sb.toString();
    }
}

