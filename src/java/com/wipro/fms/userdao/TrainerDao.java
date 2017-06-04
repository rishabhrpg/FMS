/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.userdao;


import com.wipro.fms.beans.UsersBean;
import com.wipro.fms.servelets.ProAddTrainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yogi
 */
public class TrainerDao {

    public static boolean addTrainer(UsersBean task) throws ParseException, ParseException {
                 try{
            Connection conn = DBHelper.getDbConnection();
            java.util.Date date = new java.util.Date();
            
            PreparedStatement pst = conn.prepareStatement("insert into users(id,firstname,lastname,username,password,dob,doj,email,contact_no,address,role)values(users_id.nextval,?,?,?,?,?,?,?,?,?,?)");
           
            System.out.println("Firstname in dao is "+task.getFirstname());
           pst.setString(1,task.getFirstname());
           pst.setString(2,task.getLastname());
           pst.setString(3,task.getUsername());
           pst.setString(4,task.getPassword());
           pst.setDate(5,  task.getDob());
           
           
           System.out.println(java.time.LocalDate.now());
            
           pst.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
           pst.setString(7,task.getEmail());
           pst.setString(8,task.getContact_no());
           pst.setString(9,task.getAddress());
           pst.setString(10,"trainer");
           return pst.executeUpdate()==1;
             
        }catch(SQLException x){
              Logger.getLogger(ProAddTrainer.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
    }
    
}
