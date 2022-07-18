package com.example.demo.service;

import java.io.IOException;

public interface MapreduceService {
    void cleanCSV(String jobName,String InputPath,String OutputPath) throws IOException, ClassNotFoundException, InterruptedException;

}
