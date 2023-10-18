/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.ManagerDAO;
import Model.Account;
import Model.Class1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author acer
 */
public class managerViewClassList extends HttpServlet {
   
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
            out.println("<title>Servlet managerViewClassList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managerViewClassList at " + request.getContextPath () + "</h1>");
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
        String cID = request.getParameter("courseID");
        
//        check user's authority by role
        if(user.getRoleID()!=1)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
            ManagerDAO dao=new ManagerDAO();
            List<Class1> class1 = dao.getClassByCourseID(cID);
            
            request.setAttribute("courseID",cID);
            request.setAttribute("class1", class1);
            request.getRequestDispatcher("manager-ViewClassList.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account)session.getAttribute("user");
        String cID = request.getParameter("courseID");
        String className = request.getParameter("className");
        String classID =  className+"_"+cID;
        
        
//        check user's authority by role
        if(user.getRoleID()!=1){
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
            ManagerDAO dao=new ManagerDAO();
            List<Class1> class1 = dao.getClassByCourseID(cID);
            dao.AddClass(classID,className,cID);       
            response.sendRedirect("managerViewClassList?courseID="+cID);
         
//            request.setAttribute("classID", classID);
//            request.setAttribute("className", className);
//            request.setAttribute("courseID",cID);
//            request.setAttribute("class1", class1);
//            request.getRequestDispatcher("manager-ViewClassList.jsp").forward(request, response);
           
        
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
