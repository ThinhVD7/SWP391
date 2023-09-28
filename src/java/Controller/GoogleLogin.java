/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.GoogleSupport;
import Model.GoogleDTO;
import Dal.DAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DMX
 */
@WebServlet(name = "GoogleLogin", urlPatterns = {"/GoogleLogin"})
public class GoogleLogin extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String accessToken = GoogleSupport.getToken(code);
        GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
        String email = userToken.getEmail();
        DAO dao = new DAO();
        PrintWriter pr = response.getWriter();
        Account a;
        a = dao.getUser(email);
        request.getSession().setAttribute("acc", a);

        if (a == null) {
            request.setAttribute("mess", "Your email is not accepted!!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } else {
            if (a.roleID == 0) {
                request.getSession().setAttribute("user", a);
                response.sendRedirect("admin");
            } else if (a.roleID == 1) {
                request.getSession().setAttribute("user", a);
                response.sendRedirect("managerHome");
            } else if (a.roleID == 2) {
                request.getSession().setAttribute("user", a);
                response.sendRedirect("lecturer-homepage.jsp");
            } else {
                request.getSession().setAttribute("user", a);
                response.sendRedirect("student");
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
