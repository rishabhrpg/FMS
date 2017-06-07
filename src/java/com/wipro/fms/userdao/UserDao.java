package com.wipro.fms.userdao;
import com.wipro.fms.beans.UsersBean;
import java.sql.*;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marvel
 */
public class UserDao {    
    public static int save(UsersBean user){        
        //PreparedStatement pst = conn.prepareStatement("insert into ")
        //To-Do will be implemented
        return 0;        
    }
    public static UsersBean login(UsersBean user){
        try{
            Connection conn = DBHelper.getDbConnection();
            
            PreparedStatement pst = conn.prepareStatement("select * from users where username =  ? and password = ?");
            pst.setString(1,user.getUsername());
            pst.setString(2,user.getPassword());
            ResultSet rs = pst.executeQuery();
            int rows=0;            
            while(rs.next()){
                user.setRole(rs.getString("role"));
                rows++;
            }
            if(rows==1){
                return user;
            }else{
                return null;
            }
        }catch(Exception x){
            System.out.println("Error : "+x);
            return null;
        }
    }
    public static String getUserData(Connection conn,HttpSession session,String col) throws SQLException{
        String user = session.getAttribute("username").toString();
        System.out.println("col name : "+col);
        PreparedStatement pst = conn.prepareStatement("select "+col+" from users where username = ?");        
        pst.setString(1,user);
        ResultSet rs = pst.executeQuery();
        String data = null;        
        while(rs.next()){
            data =  rs.getString(1);
            break;
        }        
        System.out.println("returning : "+data);
        return data;
    }
}

