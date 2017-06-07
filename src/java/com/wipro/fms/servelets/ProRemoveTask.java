/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wipro.fms.servelets;

import com.wipro.fms.beans.TaskBean;
import com.wipro.fms.userdao.DBHelper;
import com.wipro.fms.userdao.TaskDao;
import com.wipro.fms.userdao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yogi
 */
public class ProRemoveTask extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection conn = DBHelper.getDbConnection();
             HttpSession session = request.getSession();
            String name=request.getParameter("name");
            
             TaskBean task = new TaskBean();
             task.setName(name);
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
             if(TaskDao.removeTask(task))
             {
                 System.out.println("task addeded successfully");
               
               out.println("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
               out.print("<div class=\"w3-panel w3-green  w3-card w3-round-xxlarge\">\n" +
                "  <h3>Success!</h3>\n" +
                "  <p>Task "+task.getName()+" is removed successfully.</p>\n" +
                "</div> ");
              
             }
             else
             {
                  out.println("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
               out.print("<div class=\"w3-panel w3-red  w3-card w3-round-xxlarge\">\n" +
                "  <h3>Warning!</h3>\n" +
                "  <p>Can not remove task assigned to a trainer</p>\n" +
                "</div> ");
              
             }
              request.getRequestDispatcher("index.footer.html").include(request, response);  
        }catch(Exception ex){ 
            
                 Logger.getLogger(ProUpdateTask.class.getName()).log(Level.SEVERE, null, ex);
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
