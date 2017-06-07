/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.userdao;


import com.wipro.fms.beans.TaskBean;
import com.wipro.fms.beans.UsersBean;
import com.wipro.fms.servelets.ProAddTrainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
           //System.out.println(java.time.LocalDate.now());
           pst.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
           pst.setString(7,task.getEmail());
           pst.setString(8,task.getContact_no());
           pst.setString(9,task.getAddress());
           pst.setString(10,"trainer");
           pst.executeUpdate();
           
           PreparedStatement st = conn.prepareStatement("Select id from users where username=?");
           st.setString(1,task.getUsername());
           ResultSet rs=st.executeQuery();
           rs.next();
           
           int id=rs.getInt("id");
           
           pst=conn.prepareStatement("insert into spec(id,name,user_id) values(spec_id.nextval,?,?)");
           pst.setString(1,task.getSpec());
           pst.setInt(2,id);
             

           return pst.executeUpdate()==1;
                   }catch(SQLException x){
              Logger.getLogger(ProAddTrainer.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
    }

    public static boolean updateTrainer(UsersBean task) {
                try{
                    Connection conn = DBHelper.getDbConnection();            
            java.util.Date date = new java.util.Date();
            System.out.println("Username value is "+task.getUsername());
            PreparedStatement pst = conn.prepareStatement("update users set firstname=?,lastname=?,username=?,password=?,dob=?,doj=?,email=?,contact_no=?,address=?,role=? where username=? and role='trainer'");
            //PreparedStatement pst = conn.prepareStatement("update users set firstname='as',lastname='hh',username='hh',password='hh',email='zz',contact_no=123,address='gfchfc' where username='amar' and role='trainer'");
            System.out.println("Firstname in dao is "+task.getFirstname());
           pst.setString(1,task.getFirstname());
           pst.setString(2,task.getLastname());
           pst.setString(3,task.getUsername());
           pst.setString(4,task.getPassword());
           pst.setDate(5,  task.getDob());
           //System.out.println(java.time.LocalDate.now());
           pst.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
           pst.setString(7,task.getEmail());
           pst.setString(8,task.getContact_no());
           pst.setString(9,task.getAddress());
           pst.setString(10,"trainer");
           pst.setString(11,task.getUsername());
           pst.executeUpdate();
           
           PreparedStatement st = conn.prepareStatement("Select id from users where username=?");
           st.setString(1,task.getUsername());
           ResultSet rs=st.executeQuery();
           rs.next();
           int id=rs.getInt("id");
           pst=conn.prepareStatement("update spec set name=? where user_id=?");
           pst.setString(1,task.getSpec());
           pst.setInt(2,id);
             

           return pst.executeUpdate()==1;
                   }catch(SQLException x){
              Logger.getLogger(ProAddTrainer.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
    }

    public static boolean removeTrainer(UsersBean task, HttpServletRequest request,HttpServletResponse response,PrintWriter out,HttpSession session) throws ServletException, IOException, SQLException {
         try{
            Connection conn = DBHelper.getDbConnection();
            System.out.println("username os "+task.getUsername() );
            
            conn.setAutoCommit(false);
            try{
                String id = TrainerDao.getUserData(conn, task, "id");
                System.out.println("Userid for username "+task.getUsername()+" is  : "+id);
                
                PreparedStatement pst = conn.prepareStatement("delete from spec where user_id=?");
                pst.setInt(1,Integer.parseInt(id));            
                pst.executeUpdate();
            
                pst = conn.prepareStatement("delete from users where username=?");
                pst.setString(1,task.getUsername());
                conn.commit();
                conn.setAutoCommit(true);
                pst.executeUpdate();
                return true;
            }
            catch(SQLException sq){
                conn.rollback();
                request.getRequestDispatcher("index.head.html").include(request, response);
               request.getRequestDispatcher("welcome.nav.html").include(request, response);
                out.println("<br><div class='container-fluid'>");
               out.println("<div class='row'>"); 
               out.println("<div class='col-md-3 col-sm-4 animated fadeIn'>");
             
               out.println("<div class=\"\">\n" +
                "<div class=\"w3-card-4 test\" style=\"color:#ffffff;background-color:#0088cc;width:92%;\">\n" +
                "  <img src=\"img_avatar3.png\" alt=\"Avatar\" style=\"width:100%;opacity:0.85\">\n" +
                "  <div class=\"w3-container \" >\n" +
                "  <h4><b>"+UserDao.getUserData(conn,session,"firstname")+" "+UserDao.getUserData(conn,session,"lastname")+"</b></h4>    \n");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Username : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"username"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>"); 
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Joining: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"doj"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Birth: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"dob"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Contact No: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"contact_no"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Email : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"email"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Address: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"address"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Account Type: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"role"));
                    out.println("</div>");
                out.println("</div>");
                
                out.println("<div class='clear-fix'><br></div>");
                out.println("  </div>\n" +
                "</div>\n" +
                "<br>\n" +
                "</div>");
               out.println("</div>");
               out.println("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
               out.print("<div class=\"w3-panel w3-red  w3-card w3-round-xxlarge\">\n" +
                "  <h3>Warning!</h3>\n" +
                "  <p>Data base error occured please try again after some time all changes have been reverted.</p>\n" +
                "</div> ");
               request.getRequestDispatcher("index.footer.html").include(request, response);
                 Logger.getLogger(ProAddTrainer.class.getName()).log(Level.SEVERE, null,sq);
            }
        }catch(SQLException x){
              Logger.getLogger(ProAddTrainer.class.getName()).log(Level.SEVERE, null, x);
            
               request.getRequestDispatcher("index.head.html").include(request, response);
               request.getRequestDispatcher("welcome.nav.html").include(request, response);
                out.println("<br><div class='container-fluid'>");
               out.println("<div class='row'>"); 
               out.println("<div class='col-md-3 col-sm-4 animated fadeIn'>");
                Connection conn = DBHelper.getDbConnection();
               out.println("<div class=\"\">\n" +
                "<div class=\"w3-card-4 test\" style=\"color:#ffffff;background-color:#0088cc;width:92%;\">\n" +
                "  <img src=\"img_avatar3.png\" alt=\"Avatar\" style=\"width:100%;opacity:0.85\">\n" +
                "  <div class=\"w3-container \" >\n" +
                "  <h4><b>"+UserDao.getUserData(conn,session,"firstname")+" "+UserDao.getUserData(conn,session,"lastname")+"</b></h4>    \n");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Username : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"username"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>"); 
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Joining: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"doj"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Birth: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"dob"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Contact No: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"contact_no"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Email : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"email"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Address: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"address"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Account Type: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(conn,session,"role"));
                    out.println("</div>");
                out.println("</div>");
                
                out.println("<div class='clear-fix'><br></div>");
                out.println("  </div>\n" +
                "</div>\n" +
                "<br>\n" +
                "</div>");
               out.println("</div>");
               out.println("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
               out.print("<div class=\"w3-panel w3-red  w3-card w3-round-xxlarge\">\n" +
                "  <h3>Warning!</h3>\n" +
                "  <p>Can not remove trainer assigned to a task</p>\n" +
                "</div> ");
               request.getRequestDispatcher("index.footer.html").include(request, response);  
            return false;
        }
        return false;
    }

    private static String getUserData(Connection conn, UsersBean task, String col) throws SQLException {
        String user = task.getUsername();
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
