package com.example.demo.mapper;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
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
public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Autowired
    private FileSystem system;

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

            Workbook wb = readXls(filename);
            String[] cellResult = new String[wb.getNumberOfSheets()];
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                Sheet sheet = wb.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                String modifiedFileName=filename.replace("xls","log");
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

    public static void readCSV() throws IOException {
       int numRows;
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }
}
