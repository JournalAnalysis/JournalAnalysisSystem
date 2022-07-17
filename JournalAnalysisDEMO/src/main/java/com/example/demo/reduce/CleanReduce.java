package com.example.demo.reduce;

import com.example.demo.bean.CsvBean;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CleanReduce extends Reducer<Text,CsvBean,Text,CsvBean> {
    @Override
    protected void reduce(Text key, Iterable<CsvBean> values, Context context) throws IOException, InterruptedException {
        for(CsvBean value:values){
            context.write(key,value);
        }

    }
}
