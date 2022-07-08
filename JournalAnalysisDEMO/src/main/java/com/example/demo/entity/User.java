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

    private String ccode;


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Id
    private String uname;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    private String cname;

    private String auth;

    private String type;

    private String utime;
}
