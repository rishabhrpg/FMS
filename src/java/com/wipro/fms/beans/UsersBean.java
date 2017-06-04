package com.wipro.fms.beans;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marvel
 */
public class UsersBean {    
    private String firstname;
    private String lastname;
    private Date dob;    
    private Date doj;
    private String contact_no;

    public UsersBean(String username, String password) {
        this.username = username;
        this.password = password;
    }
    private String email;
    private String address;
    private String username;
    private String password;
    private String role;  
    private String spec;

    public UsersBean() {
       
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public UsersBean(UsersBean user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UsersBean(String firstname, String lastname, Date dob, Date doj, String contact_no, String email, String address, String username, String password, String role,String spec) {
        this.firstname = firstname;
        System.out.println("Getting firstname in beans "+firstname);
        this.lastname = lastname;
        this.dob = dob;
        this.doj = doj;
        this.contact_no = contact_no;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.spec=spec;
    }

    @Override
    public String toString() {
        return "UsersBean{" + "firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + ", doj=" + doj + ", contact_no=" + contact_no + ", email=" + email + ", address=" + address + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
}
