package com.example.attendancesystem.models;

public class student {
    public String getSid() {
        return sid;
    }

    public student(String sid, String sname, String att, String rate) {
        this.sid = sid;
        this.sname = sname;
        this.att = att;
        this.rate = rate;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    String sid, sname, att, rate;
}
