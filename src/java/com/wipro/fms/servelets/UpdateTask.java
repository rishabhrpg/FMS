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
public class UpdateTask extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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
                "  <h4><b>"+UserDao.getUserData(session,"firstname")+" "+UserDao.getUserData(session,"lastname")+"</b></h4>    \n");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Username : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"username"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Joining: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"doj"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Date of Birth: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"dob"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Contact No: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"contact_no"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Email : ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"email"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Address: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"address"));
                    out.println("</div>");
                out.println("</div>");
                out.println("<div class='row'>");
                    out.println("<div class='col-xs-6'>");
                        out.println("Account Type: ");
                    out.println("</div>");
                    out.println("<div class='col-xs-6'>");
                        out.println(UserDao.getUserData(session,"role"));
                    out.println("</div>");
                out.println("</div>");
                
                out.println("<div class='clear-fix'><br></div>");
                out.println("  </div>\n" +
                "</div>\n" +
                "<br>\n" +
                "</div>");
               out.println("</div>");
               out.print("<div class='col-md-4 col-sm-8  animated fadeInDown'>");
               
               out.println("<form action=\"ProUpdateTask\" class=\"w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin w3-round-xlarge\" method=\"POST\">\n" +
                    "<h4 class=\"w3-center\">Update a Task</h4>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>        \n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>\n" +
                    "        \n");
                    out.println("<select name='name' class='form-control'>");                    
                    Connection conn = DBHelper.getDbConnection();
                    PreparedStatement pst = conn.prepareStatement("select name from tasks");
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        out.println("<option>"+rs.getString(1));
                    }
                    out.println("</select>");
                    
                    out.println("</div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date' id='datetimepicker1'>        \n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"glyphicon glyphicon-calendar\"></span>\n" +
                    "        </span>\n" +
                    "        <input type='text' name = \"stime\" value=\"Starting Date and Time\" id=\"datetimepicker\" data-date-format=\"yyyy-mm-dd hh:mm:ss\" class=\"form-control\" />\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date' id='datetimepicker1'>        \n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"glyphicon glyphicon-calendar\"></span>\n" +
                    "        </span>\n" +
                    "        <input type='text' name = 'etime' value=\"Ending Date and Time\" id=\"datetimepicker2\" data-date-format=\"yyyy-mm-dd hh:mm:ss\" class=\"form-control\" />\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<script>\n" +
                    "    $('#datetimepicker').datetimepicker();\n" +
                    "    $('#datetimepicker2').datetimepicker(); \n" +
                    "</script>\n" +
                    "<div class=\"row w3-center\">\n" +
                    "    <button class=\"w3-button w3-round-xlarge w3-section w3-blue w3-ripple w3-padding\">Save</button>\n" +
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
        DBHelper.getDbConnection().close();
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
            Logger.getLogger(UpdateTask.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateTask.class.getName()).log(Level.SEVERE, null, ex);
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
