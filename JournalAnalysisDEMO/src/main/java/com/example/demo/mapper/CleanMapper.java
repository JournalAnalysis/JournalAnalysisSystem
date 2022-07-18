package com.example.demo.mapper;

import com.example.demo.bean.CsvBean;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class CleanMapper extends Mapper<LongWritable, Text, Text, CsvBean> {
    private Text outKey =new Text();
    private CsvBean outValue=new CsvBean();



//    public static void readCSV(String fileName) throws IOException {
//       int numRows;
//       try{
//           FileInputStream fis = new FileInputStream(fileName);
//
//    }
//       catch (IOException E){
//           E.printStackTrace();
//       }
//    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable,Text,Text,CsvBean>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String[] csvComments=line.split(",");
        String ip=csvComments[0];
        String access_time=csvComments[1];
        String url=csvComments[2];
        String status_code=csvComments[3];
        String traffic=csvComments[4];
        String referer=csvComments[5];
        String client=csvComments[6];

        outValue.setIp(ip);
        outValue.setAccess_time(access_time);
        outValue.setUrl(url);
        outValue.setStatus_code(status_code);
        outValue.setTraffic(traffic);
        outValue.setReferer(referer);
        outValue.setClient(client);
        context.write(outKey,outValue);
}
}
