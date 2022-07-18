package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class Log {
    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    @Id
    private String logid;

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    private String logname;

    public String getLoginf() {
        return loginf;
    }

    public void setLoginf(String loginf) {
        this.loginf = loginf;
    }

    private String loginf;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    private String cname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    private String uname;

    private String uptime;

    private String loglocation;

    private String loglink;

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getLoglocation() {
        return loglocation;
    }

    public void setLoglocation(String loglocation) {
        this.loglocation = loglocation;
    }

    public String getLoglink() {
        return loglink;
    }

    public void setLoglink(String loglink) {
        this.loglink = loglink;
    }

    public String getLogstate() {
        return logstate;
    }

    public void setLogstate(String logstate) {
        this.logstate = logstate;
    }

    private String logstate;

    public String getLogauth() {
        return logauth;
    }

    public void setLogauth(String logauth) {
        this.logauth = logauth;
    }

    private String logauth;

}
