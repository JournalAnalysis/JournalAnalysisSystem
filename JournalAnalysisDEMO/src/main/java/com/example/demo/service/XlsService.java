package com.example.demo.service;

import com.example.demo.bean.XlsBean;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class XlsService {
    @Autowired
    private FileSystem fileSystem;
    private XlsBean outValue=new XlsBean();
    private static FormulaEvaluator evaluator;

    public Cell[][] readXls(String srcPath,String fileName) {
        Workbook wb = getSheets(srcPath, fileName);
        Cell[][] result = null;
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            result=new Cell[sheet.getLastRowNum()+1][];
            for (int numRow = 0; numRow <= sheet.getLastRowNum(); numRow++) {
                Row row = sheet.getRow(numRow);
                Cell[] tmpResult=new Cell[row.getLastCellNum()];

                for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
                    tmpResult[numCell]=row.getCell(numCell);
                }
                result[numRow]=tmpResult;
            }

        }
        return result;
    }

    public  Workbook getSheets(String srcPath,String fileName) {
        if (fileName.isEmpty()) {
            return null;
        }

        try {
            Path src=new Path(srcPath+"/"+fileName);
            FSDataInputStream fsDataInputStream=fileSystem.open(src);
            if(fileName.contains("xlsx")){
                return new XSSFWorkbook(fsDataInputStream);
            }
            else{
                return new HSSFWorkbook(fsDataInputStream);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getValue(Cell cell) {
        String val = null;
        switch(cell.getCellType()) {
            case FORMULA:  //公式类型
                // 先计算表达式
                val = String.valueOf(evaluator.evaluate(cell).getNumberValue());
                break;
            case BOOLEAN:  //布尔类型
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            case STRING:   // 字符串类型
                val = cell.getStringCellValue().trim();
                break;
            case NUMERIC:  // 数值类型
                // 日期格式
                if(DateUtil.isCellDateFormatted(cell)) {
                    val =   Date2Str(cell.getDateCellValue(), "dd-MM-yyyy HH:mm:ss");
                }else {
                    // 四舍五入
                    val = new DecimalFormat("xxxx").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型
                break;
        }
        return val;
    }

    public static String Date2Str(Date date, String format){
        // Date -> LocalDateTime -> String
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),zone);
        return df.format(localDateTime);
    }
    //将用户上传的文件输出到hdfs中
    public void readXlsContent(String targetLocation,String srcLocation,String filename) throws IOException {
        try {
            Cell[][] cells=readXls(srcLocation,filename);
            String modifiedFileName = "";
            if (filename.contains("xlsx")) {
                modifiedFileName = filename.replace("xlsx", "log");
            }
            else {
                modifiedFileName = filename.replace("xls", "log");
            }

            Path src = new Path(targetLocation + "/" + modifiedFileName);
            FSDataOutputStream fsDataOutputStream = fileSystem.create(src);
            for (int i = 0; i < cells.length; i++) {

                outValue.setId(getValue(cells[i][0]));
                outValue.setIp(getValue(cells[i][1]));
                outValue.setAccess_time(getValue(cells[i][2]));
                outValue.setUrl(getValue(cells[i][3]));
                outValue.setStatus_code(getValue(cells[i][4]));
                outValue.setTraffic(getValue(cells[i][5]));
                outValue.setReferer(getValue(cells[i][6]));
                outValue.setClient(getValue(cells[i][7]));
                fsDataOutputStream.write(outValue.toString().getBytes());
            }
            fsDataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

