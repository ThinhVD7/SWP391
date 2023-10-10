/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.ManagerDAO;
import Model.Account;
import Model.Class1;
import Model.Lecturer;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class managerViewLecturer extends HttpServlet {
   
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
            out.println("<title>Servlet managerViewLecturer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managerViewLecturer at " + request.getContextPath () + "</h1>");
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
    throws ServletException, IOException {
       HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account)session.getAttribute("user");
        String cid = request.getParameter("CID");
        String courseID = request.getParameter("courseID");
//        check user's authority by role
        if(user.getRoleID()!=1)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
            ManagerDAO dao=new ManagerDAO();
            List<Lecturer> lecturer = dao.getlecturerByClass(cid);
            List<Student> student = dao.getstudentByClass(cid);
            List<Lecturer> addlecturer = dao.getAllCourselecturer();
            List<Student> addstudent = dao.getAllCoursestudent();
            
            Class1 classInformation = dao.getClassByID(cid);
            request.setAttribute("lecturer", lecturer);
            request.setAttribute("student", student);
            request.setAttribute("addlecturer", addlecturer);
            request.setAttribute("addstudent", addstudent);
            request.setAttribute("courseID", courseID);
            request.setAttribute("classInfo", classInformation);
            request.setAttribute("classID", cid);
            request.getRequestDispatcher("manager-ClassDetail.jsp").forward(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String lecturerAdd = request.getParameter("add");
        String classID = request.getParameter("classID");
        response.getWriter().print(lecturerAdd + " " + classID);
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
