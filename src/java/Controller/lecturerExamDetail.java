/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author ROG
 */
public class lecturerExamDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet lecturerExamDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lecturerExamDetail at " + request.getParameter("examID")+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        //check active status
        Account user = (Account)session.getAttribute("user");
        if(user.getStatus()==0)
            {
                session.removeAttribute("user");
                request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        Exam thisExam = dao.loadAExam(request.getParameter("examID"));
        //session thisExam
        session.setAttribute("sessionThisExam", thisExam);
        if(LocalDateTime.now().compareTo(LocalDateTime.parse(thisExam.getStartDate()))>0)
            request.setAttribute("notAllowToEdit", "notAllowToEdit");
        
        request.setAttribute("timeLimit", dao.getStringFormattedDate("timeLimit", thisExam.getTimeLimit()));
        request.setAttribute("startDate", dao.getStringFormattedDate("dateTime", thisExam.getStartDate()));
        request.setAttribute("endDate", dao.getStringFormattedDate("dateTime", thisExam.getEndDate()));
        request.getRequestDispatcher("lecturerExamDetail.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
