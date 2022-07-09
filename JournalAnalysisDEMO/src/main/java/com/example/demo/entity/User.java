package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Id
    private String uname;

    public String getPassword() {
        return upassword;
    }

    public void setPassword(String password) {
        this.upassword = password;
    }

    private String upassword;

    private String uemail;

    public String getEmail() {
        return uemail;
    }

    public void setEmail(String email) {
        this.uemail = email;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAuth() {
        return uauth;
    }

    public void setAuth(String auth) {
        this.uauth = auth;
    }

    public String getType() {
        return utype;
    }

    public void setType(String type) {
        this.utype = type;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }
    private String cname;

    private String uauth;

    private String utype;

    private String utime;
    private String ccode;
}
