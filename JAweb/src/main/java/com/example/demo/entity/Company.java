package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCinf() {
        return cinf;
    }

    public void setCinf(String cinf) {
        this.cinf = cinf;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    @Id
    private String cname;

    private String cinf;

    private String ccode;
}
