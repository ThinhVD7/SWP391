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
        String Password = request.getParameter("password0");
        String NPassword = request.getParameter("password1");
        String RNPassword = request.getParameter("password2");
        HttpSession session = request.getSession();
        DAO dao = new DAO();
        Account acc = (Account) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect("Login.jsp");
        } else {
            String email = acc.getEmail();
            Account a = dao.getAccountLogin(email, Password);
            if (a == null) {
                request.setAttribute("msg", "Old password is incorrect");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
            if (!NPassword.equals(RNPassword)) {
                request.setAttribute("msg", "New password not equals renew password");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            } else {
                dao.changePassword(NPassword, email);
                acc.setPassword(NPassword);
                session.setAttribute("user", acc);
                request.setAttribute("fmsg", "Password was successfully changed");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
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
