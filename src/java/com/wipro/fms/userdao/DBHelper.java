/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.userdao;
import java.sql.*;
/**
 *
 * @author Marvel
 */
public class DBHelper {    
    public static Connection getDbConnection(){    
	try{
                Connection con=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","htikn","htikn");
                con.setAutoCommit(true);
                System.out.println("Jud gaya oyee.......");
                return con;
	}catch(Exception ex){
            System.out.println("maaa ki chuuuu");
            System.out.println(ex);
            return null;
        }	
    }
}
