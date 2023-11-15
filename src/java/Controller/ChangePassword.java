/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *
 * @author DMX
 */
public class ChangePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        //block check if user have logged in, if not then return to home
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
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
        //block check if user have logged in, if not then return to home
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        
        Account user = (Account)session.getAttribute("user");
        String oldPassword = request.getParameter("password0");
        String newPassword = request.getParameter("password1");
        String repeatNewPassword = request.getParameter("password2");
        DAO dao = new DAO();
        String email = user.getEmail();
        Account checkOldPassword = dao.getAccountLogin(email, oldPassword);
        //check if old password existed
        if (checkOldPassword == null) 
        {
            request.setAttribute("msg", "Old password is incorrect");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
        //check if the repeat is right
        else if (!newPassword.equals(repeatNewPassword)) {
            request.setAttribute("msg", "New password not equals renew password");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } 
        //change the password
        else 
        {
            dao.changePassword(dao.encodeSHA1(newPassword), email);
            user.setPassword(dao.encodeSHA1(newPassword));
            session.setAttribute("user", user);
            request.setAttribute("fmsg", "Password was successfully changed");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
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
