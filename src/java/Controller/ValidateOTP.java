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

/**
 *
 * @author DMX
 */
public class ValidateOTP extends HttpServlet {

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
        int value = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        //block check if user have logged in, if not then return to home
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        int otp = (int) session.getAttribute("otp");

        if (value == otp) {
//            request.setAttribute("email", request.getParameter("email"));
            String email = (String) session.getAttribute("email");

//            request.setAttribute("email", request.getParameter("email"));
            DAO dao = new DAO();
            Account a;
            a = dao.getUser(email);
            request.getSession().setAttribute("user", a);
            //      response.sendRedirect("indexStudent.jsp");

            int roleId = (int) session.getAttribute("roleId");

            if (roleId == 0) {
                request.getSession().setAttribute("user", a);

                response.sendRedirect("admin");
            } else if (roleId == 1) {
                request.getSession().setAttribute("user", a);

                response.sendRedirect("managerHome");
            } else if (roleId == 2) {
                request.getSession().setAttribute("user", a);

                response.sendRedirect("lecturer-homepage.jsp");
            } else {
                request.getSession().setAttribute("user", a);

                response.sendRedirect("student");
            }

        } else {
            request.setAttribute("OTPmess", "WRONG OTP!");
            request.getRequestDispatcher("OTP.jsp").forward(request, response);
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
