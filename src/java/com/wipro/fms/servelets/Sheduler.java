/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.servelets;

import Helpers.Helper;
import com.wipro.fms.userdao.DBHelper;
import com.wipro.fms.userdao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marvel
 */
public class Sheduler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection conn = DBHelper.getDbConnection();
              HttpSession session = request.getSession();
            if(Helper.validateManager(session)){
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
               out.print("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
              out.print("<form action=\"Sheduler\" class=\"w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin w3-round-xlarge\" method=\"POST\">\n" +
"<h4 class=\"w3-center\">Schedule Log</h4>\n");

              
               Statement st = conn.createStatement();
               ResultSet rs3 = st.executeQuery("select max(day) from schedule_log");
               rs3.next();
               
               if(rs3.getDate(1).toString().equals(java.sql.Date.valueOf(java.time.LocalDate.now().toString()).toString())){
                   System.out.println("Not truncating");
               }else{
                   Statement st5 = conn.createStatement();
                   System.out.println("database date "+rs3.getDate(1));
                   System.out.println("Today "+java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
                   st5.executeUpdate("truncate table schedules");
                   System.out.println("truncating table schedule");
               }
                       
               
               PreparedStatement pst = conn.prepareStatement("select * from tasks");
               ResultSet tasks = pst.executeQuery();
                
               PreparedStatement pst2 = conn.prepareStatement("select * from schedules where task_id = ?");
               
               PreparedStatement pst3 = conn.prepareStatement("select * from spec where name=? ");               
               
               PreparedStatement pst4 = conn.prepareStatement("select * from schedules where user_id =? ");
               
               PreparedStatement pst5 = conn.prepareStatement("insert into schedules values(schedule_id.nextval,?,?) ");
               String []failed = null;
               int taskcount=0;
                boolean isscheduled=false;
               while(tasks.next()){
                   pst2.setInt(1,tasks.getInt("id"));
                   ResultSet rs = pst2.executeQuery();
                   int count=0;
                   while(rs.next()){
                       count++;
                   }
                   if(count==0){   
                       taskcount++;
                        pst3.setString(1,tasks.getString("name"));
                        ResultSet spec = pst3.executeQuery();
                        
                        while(spec.next()){
                            
                            out.println("<br><br> task without trainer "+tasks.getString("name"));
                             out.println(" <br><br>trainer id for spec  "+tasks.getString("name") + " id is "+spec.getString("user_id"));
                            
                            pst4.setInt(1, spec.getInt("user_id"));
                            ResultSet avaliable_trainers = pst4.executeQuery();
                             out.println("<br>checking for availablity of trainer");
                             boolean isavaliable=true;
                             
                            if(avaliable_trainers.next()){
                                isavaliable=false;
                                out.println("<br>Trainer id "+avaliable_trainers.getString("id") +" is not avaliable");
                                
                            }
                            out.println("<br>is avaliable : "+isavaliable );
                            if(isavaliable){
                                
                               out.println("<br>Trainer id "+spec.getInt("user_id") +" is avaliable");
                               pst5.setInt(1, tasks.getInt("id")); 
                               pst5.setInt(2, spec.getInt("user_id"));
                               pst5.execute();
                                    out.println("<br>Scheduled Succesfully");
                                    isscheduled=true;
                             PreparedStatement st10 = conn.prepareStatement("insert into schedule_log values(?)");
                             st10.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
                             st10.execute();
                                 System.out.println(java.sql.Date.valueOf(java.time.LocalDate.now().toString()));
                               
                              Statement st100 = conn.createStatement();
                              double hours;
                              hours = tasks.getTimestamp("etime").getTime() - tasks.getTimestamp("stime").getTime();
                              System.out.println("\n\n\n\n Hours : "+hours);
                              st100.executeUpdate("update users set work_hours = work_hours+"+hours);                              
                            }
                        } 
                   }else{
                       
                             out.println("<br> task "+tasks.getString("name")+ " already scheduled");
                             isscheduled=true;
                                          }
                    if(isscheduled==false){
                        out.println("<br>There is no trainer avaliable for task "+tasks.getString("name")+ " or speacialized trainers not avaliable");
                    }      
                    isscheduled=false;
               }
               if(taskcount==0)
                    out.println("No task for scheduling");
             out.print("<div class=\"row w3-center\">\n" +
"    <button class=\"w3-button w3-round-xlarge w3-section w3-blue w3-ripple w3-padding\">Re-Schedule</button>\n" +
"</div>\n" +
"</form>");
                request.getRequestDispatcher("index.footer.html").include(request, response);
            }else{
                request.getRequestDispatcher("index.head.html").include(request, response);
                request.getRequestDispatcher("index.nav.html").include(request, response);
                out.print("<div class='container'><div class='alert alert-danger'>Session Exired or not admin session</div></div>");
                request.getRequestDispatcher("index.LoginForm.html").include(request, response);
                request.getRequestDispatcher("index.footer.html").include(request, response);
            }
              
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Sheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Sheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
