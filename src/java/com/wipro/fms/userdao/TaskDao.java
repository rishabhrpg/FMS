/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.userdao;

import com.wipro.fms.beans.TaskBean;
import com.wipro.fms.servelets.ProUpdateTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yogi
 */
public class TaskDao {

    public static boolean addTask(TaskBean task) {
         try{
            Connection conn = DBHelper.getDbConnection();
            
            PreparedStatement pst = conn.prepareStatement("insert into tasks(id,name,stime,etime) values(tasks_id.nextval,?,?,?)");
            pst.setString(1,task.getName());
            pst.setTimestamp(2,task.getStime());
            pst.setTimestamp(3,task.getEtime());            
            if(pst.executeUpdate()==1){
                return true;
            }else
                return false;
             
        }catch(Exception x){
            System.out.println("Error : "+x);
            return false;
        }
        
    }

    public static boolean updateTask(TaskBean task) {
        try{
            Connection conn = DBHelper.getDbConnection();
            
            PreparedStatement pst = conn.prepareStatement("update tasks set stime=?,etime=? where name=?");
            pst.setString(3,task.getName());
            pst.setTimestamp(1,task.getStime());
            pst.setTimestamp(2,task.getEtime());            
            if(pst.executeUpdate()==1){
                return true;
            }else
                return false;
             
        }catch(Exception x){
            System.out.println("Error : "+x);
            return false;
        }
       
    }

    public static boolean removeTask(TaskBean task) {
        try{
            Connection conn = DBHelper.getDbConnection();
            
            PreparedStatement pst = conn.prepareStatement("delete from tasks where name=?");
            pst.setString(1,task.getName());
            
            pst.executeUpdate();
             return true;
        }catch(SQLException x){
            System.out.println("Error : "+x);
             Logger.getLogger(ProUpdateTask.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        
    }
    
}
