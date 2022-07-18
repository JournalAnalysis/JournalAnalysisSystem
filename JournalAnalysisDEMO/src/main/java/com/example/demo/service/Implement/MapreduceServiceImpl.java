package com.example.demo.service.Implement;

import com.example.demo.config.MapRedConfig;
import com.example.demo.service.HDFSService;
import com.example.demo.service.MapreduceService;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class MapreduceServiceImpl implements MapreduceService {
    @Qualifier("HDFSServiceImpl")
    @Autowired
    private HDFSService hdfsService;

    @Autowired
    private MapRedConfig mapreduceJobsConfig;

    @Override
    public void cleanCSV(String jobName, String InputPath, String OutputPath) throws IOException, ClassNotFoundException, InterruptedException {
        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(InputPath)) {
            return;
        }
        // 输出目录 = output/当前Job,如果输出路径存在则删除，保证每次都是最新的
        if (hdfsService.existFile(OutputPath)) {
            hdfsService.deleteFile(OutputPath);
        }
        mapreduceJobsConfig.getCSVReduceJobsConf(jobName, new Path(InputPath), new Path(OutputPath));
    }


}

