package com.example.demo.config;
import com.example.demo.DemoApplication;
import com.example.demo.bean.CsvBean;
import com.example.demo.mapper.CleanMapper;
import com.example.demo.reduce.CleanReduce;
import com.example.demo.service.Implement.MapreduceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import java.io.IOException;
@Slf4j
@Component
public class MapRedConfig {
    @Value("${hdfs.hdfsPath}")
    private String hdfsPath;


    public org.apache.hadoop.conf.Configuration getConfiguration(){
        org.apache.hadoop.conf.Configuration configuration= new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS",hdfsPath);
        configuration.set("mapreduce.jobtracker.address",hdfsPath);
        configuration.set("dfs.client.use.datanode.hostname", "true");
        return configuration;
    }

    public void getCSVReduceJobsConf(String jobName, Path inputPath,Path outputPath)
            throws IOException,ClassNotFoundException,InterruptedException
    {
        org.apache.hadoop.conf.Configuration conf =getConfiguration();
        Job job =Job.getInstance(conf,jobName);
        job.setJarByClass(MapreduceServiceImpl.class);
        job.setCombinerClass(CleanReduce.class);

        job.setMapperClass(CleanMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CsvBean.class);


        job.setReducerClass(CleanReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(CsvBean.class);

        FileInputFormat.addInputPath(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);

        job.waitForCompletion(true);

        log.info("Testing mapreduce");

    }
//    public void getXlsReduceJobsConf(String jobName, Path inputPath,Path outputPath)
//            throws IOException,ClassNotFoundException,InterruptedException
//    {
//        org.apache.hadoop.conf.Configuration conf =getConfiguration();
//        Job job =Job.getInstance(conf,jobName);
//        job.setMapperClass(CleanMapper.class);
//        job.setCombinerClass(CleanReduce.class);
//        job.setJarByClass(DemoApplication.class);
//        job.setReducerClass(CleanReduce.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(CsvBean.class);
//        FileInputFormat.addInputPath(job,inputPath);
//        FileOutputFormat.setOutputPath(job,outputPath);
//        job.waitForCompletion(true);
//        log.info("Testing mapreduce");
//
//
//    }
}
