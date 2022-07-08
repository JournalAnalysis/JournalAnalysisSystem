package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
