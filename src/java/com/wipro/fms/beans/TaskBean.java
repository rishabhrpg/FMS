/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.beans;

import java.sql.Timestamp;

public class TaskBean {

    public TaskBean() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "TaskBean{" + "name=" + name + ", stime=" + stime + ", etime=" + etime + '}';
    }

    public String getName() {
        return name;
    }

    public TaskBean(String name, Timestamp stime, Timestamp etime) {
        this.name = name;
        this.stime = stime;
        this.etime = etime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStime() {
        return stime;
    }

    public void setStime(Timestamp stime) {
        this.stime = stime;
    }

    public Timestamp getEtime() {
        return etime;
    }

    public void setEtime(Timestamp etime) {
        this.etime = etime;
    }
    private String name;
    private Timestamp stime;
    private Timestamp etime;
}