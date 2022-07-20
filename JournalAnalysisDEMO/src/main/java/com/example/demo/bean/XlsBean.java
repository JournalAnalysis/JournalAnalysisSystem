package com.example.demo.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class XlsBean implements Writable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String ip;
    private String access_time;
    private String url;
    private String status_code;
    private String referer;
    private String client;
    private String traffic;

    public String getIp() {
        return ip;
    }
    public XlsBean(){}

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getAccess_time() {
        return access_time;
    }

    public void setAccess_time(String access_time) {
        this.access_time = access_time;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    @Override
    public String toString(){
     return id+" "+ip+" -- "+access_time+" "+url+" "+status_code+" "+traffic+" "+referer+" "+client+"\n";
    }
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(ip);
        dataOutput.writeUTF(access_time);
        dataOutput.writeUTF(url);
        dataOutput.writeUTF(status_code);
        dataOutput.writeUTF(traffic);
        dataOutput.writeUTF(referer);
        dataOutput.writeUTF(client);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id=dataInput.readUTF();
        this.ip=dataInput.readUTF();
        this.access_time=dataInput.readUTF();
        this.url=dataInput.readUTF();
        this.status_code=dataInput.readUTF();
        this.traffic=dataInput.readUTF();
        this.referer=dataInput.readUTF();
        this.client=dataInput.readUTF();

    }
}
