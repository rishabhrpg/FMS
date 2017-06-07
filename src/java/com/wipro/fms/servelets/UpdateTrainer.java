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
public class UpdateTrainer extends HttpServlet {

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
               out.print("<div class='col-md-8 col-sm-8  animated fadeInDown'>");
               
               out.println("<form action=\"ProUpdateTrainer\" class=\"w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin w3-round-xlarge\" method=\"POST\">\n" +
                    "<h4 class=\"w3-center\">Update a Trainer</h4>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>        \n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>\n" +
                    "        \n");
                    out.println("<select onchange='changeUsername(this.value)' id='select' name='name' class='form-control'>");                    
                    
                    PreparedStatement pst = conn.prepareStatement("select firstname,lastname,username from users where role='trainer'");
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        out.println("<option value="+rs.getString("username")+">"+rs.getString(1)+" "+rs.getString(2));
                    }
                    out.print("<script> function changeUsername(user){ $(\"#username\").val(user);} </script>");
                    out.println("</select></div></div>");
                    
                    out.print("<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>\n" +
                    "        <label class=\"form-control\">First Name</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"firstname\" type=\"text\" placeholder=\"First Name\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">    \n" +
                    "    <div class='input-group date'>\n" +
                    "        <label class=\"form-control\">Last Name</label>        \n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"lastname\" type=\"text\" placeholder=\"Last Name\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>        \n" +
                    "        <label class=\"form-control\">Username</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control \" name=\"username\" id='username' type=\"text\" placeholder=\"Username\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>       \n" +
                    "        <label class=\"form-control\">Password</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"password\" type=\"password\" placeholder=\"Password\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>        \n" +
                    "        <label class=\"form-control\">Date of Birth</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-calendar\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"dob\" type=\"date\" placeholder=\"Date of Birth in YYYY/MM/DD\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>       \n" +
                    "        <label class=\"form-control\">Email</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-envelope\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"email\" type=\"email\" placeholder=\"email\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>       \n" +
                    "        <label class=\"form-control\">Contact No</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-mobile\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"contact_no\" type=\"number\" maxlegth=\"12\" placeholder=\"Contact No\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "    <div class='input-group date'>        \n" +
                    "        <label class=\"form-control\">Address</label>\n" +
                    "        <span class=\"input-group-addon\">\n" +
                    "            <span class=\"fa fa-pencil\"></span>\n" +
                    "        </span>        \n" +
                    "        <input class=\"form-control\" name=\"address\" type=\"text\" placeholder=\"Address\" required=\"required\">\n" +
                    "    </div>\n" +
                    "</div>\n" +
                            "<div class=\"form-group\">\n" +
"    <div class='input-group date'>        \n" +
"        <label class=\"form-control\">Specializtion</label>\n" +
"        <span class=\"input-group-addon\">\n" +
"            <span class=\"fa fa-pencil\"></span>\n" +
"        </span>        \n" +
"        <input class=\"form-control\" name=\"spec\" type=\"text\" placeholder=\"spec\" required=\"required\">\n" +
"    </div>\n" +
"</div>"+
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
        
        DBHelper.getDbConnection().close();
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
            Logger.getLogger(UpdateTrainer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateTrainer.class.getName()).log(Level.SEVERE, null, ex);
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
