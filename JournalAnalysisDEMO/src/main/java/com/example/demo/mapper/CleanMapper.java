package com.example.demo.mapper;

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
public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Autowired
    private FileSystem system;
    static int count;
    static int at;
    static String[] fam;
    static String[] qua;
    private Text outKey =new Text();
    private Text outValue=new Text();

    public Workbook readXls(String fileName) {
        return getSheets(fileName);
    }

    public static Workbook getSheets(String fileName) {
        if (fileName.isEmpty()) {
            return null;
        }

        try {
            InputStream inputStream = new FileInputStream(fileName);
            if(fileName.contains("xls")){
                return new HSSFWorkbook(inputStream);
             }
            if(fileName.contains("xlsx")){
                 return new XSSFWorkbook(inputStream);
             }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    //将用户上传的文件输出到hdfs中
    public void readXlsContent(String targetLocation,String filename) {
        try {
            String modifiedFileName = "";
            Workbook wb = readXls(filename);
            String[] cellResult = new String[wb.getNumberOfSheets()];
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                Sheet sheet = wb.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                if(filename.contains("xls")){
                    modifiedFileName=filename.replace("xls","log");
                }
                if(filename.contains("xlsx")){
                    modifiedFileName=filename.replace("xlsx","log");
                }
                Path src=new Path(targetLocation+"/"+modifiedFileName);
                FSDataOutputStream fsDataOutputStream = system.create(src);

                for (int numRow = 0; numRow <= sheet.getLastRowNum(); numRow++) {
                    Row row = sheet.getRow(numRow);
                    Cell cell = row.getCell(0);
                    cellResult[numRow] = cell.getStringCellValue();
                    fsDataOutputStream.write(cellResult[numRow].getBytes());
                }

                fsDataOutputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCSV(String fileName) throws IOException {
       int numRows;
       try{
           FileInputStream fis = new FileInputStream(fileName);

    }
       catch (IOException E){
           E.printStackTrace();
       }
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String key1="";
        String value1="";
        String line=value.toString();
        String[] splited = line.split(",");
        count=splited.length;
        String[] first=line.split(",");
        String[] family=new String[count];
        String[] qualifier=new String[count];
        for(int i=0;i< splited.length;i++){
            if (i==0){
                key1+=(splited[i]+"\t");
            }
            else{
                value1+=(splited[i]+"\t");
                if(at==0){
                    if (first[i].indexOf(":") > 0) {
                        family[i] = first[i].substring(0, first[i].indexOf(":"));
                        qualifier[i] = first[i].substring(first[i].indexOf(":") + 1, first[i].length());
                    } else {
                        family[i] = first[i];
                        qualifier[i] = "";
                    }

                }
            }
        }

        if(at==0){
            fam=family;
            qua=qualifier;
        }
        outKey.set(key1);
        outValue.set(value1);
        context.write(outKey,outValue);
        at++;
    }
}
