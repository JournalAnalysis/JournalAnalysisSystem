package com.example.demo.bean;
import java.sql.Timestamp;

public class SqoopBean {
    private int i;
    private Timestamp ts;

    public int getI() {
        return i;
    }

    public int setI(int i) {
        this.i = i;
        return i;
    }

    @Override
    public String toString() {
        return "sqoopBean{" +
                "i=" + i +
                ", ts=" + ts +
                '}';
    }

    public Timestamp getTs() {
        return ts;
    }

    public Timestamp setTs(Timestamp ts) {
        this.ts = ts;
        return ts;
    }
}