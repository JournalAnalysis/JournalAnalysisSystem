<<<<<<<< HEAD:JournalAnalysisDEMO/src/main/java/com/janal/demo/configuration/HadoopHDFSConfiguration.java
package com.janal.demo.configuration;
========
package main.configuration;
>>>>>>>> af56e82c5651bcb53efa45ba50fb9836be886ae2:JournalAnalysisDEMO/src/main/java/main/configuration/HadoopHDFSConfiguration.java

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



@Configuration
@Slf4j
public class HadoopHDFSConfiguration {
    @Value("{hdfs.hdfsPath}")
    private String hdfsPath;
    @Value("{hdfs.hdfsName}")
    private String hdfsName;

    @Value("{hdfs.hdfsUser")
    private String hdfsUser;
    @Bean
    public org.apache.hadoop.conf.Configuration  getConfiguration(){
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        return configuration;
    }

    @Bean
    public FileSystem getFileSystem(){
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        return fileSystem;
    }


}
