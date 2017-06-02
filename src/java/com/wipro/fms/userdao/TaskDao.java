/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.userdao;

import com.wipro.fms.beans.TaskBean;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Yogi
 */
public class TaskDao {

    public static boolean addTask(TaskBean task) {
         try{
            Connection conn = DBHelper.getDbConnection();
            
            PreparedStatement pst = conn.prepareStatement("insert into tasks(id,name,stime,etime) values(tasks_id.nextval,?,?,?)");
            pst.setString(1,task.getStime());
            pst.setString(2,task.getEtime());
            pst.setString(3,task.getName());
             if(pst.execute())
             {
                 return true;
             }
           else{
                return false;
            }
        }catch(Exception x){
            System.out.println("Error : "+x);
            return false;
        }
        
    }
    
}
