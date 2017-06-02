/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.beans;

/**
 *
 * @author Yogi
 */
public class TaskBean {
    private String name;
    private String stime;
    private String etime;
    public TaskBean(String name, String stime, String etime) {
        this.name = name;
        this.stime = stime;
        this.etime = etime;
    }

    @Override
    public String toString() {
        return "TaskBean{" + "name=" + name + ", stime=" + stime + ", etime=" + etime + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

}
