/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.Account;
import Model.Course;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author msi
 */
@WebServlet(name = "managerSearch", urlPatterns = {"/managerSearch"})
public class managerSearch extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageSearch Hello</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageSearch at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account) session.getAttribute("user");
        //check user's authority by role
        if (user.getRoleID() != 1) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ///////////////////////////////
        DAO dao = new DAO();
        List<Course> course = dao.getAllCourse();

//        //after fix database
        LocalDate today = LocalDate.now();
        HashMap<String, Boolean> deleteNotAllowMap = new HashMap<String, Boolean>();
        for (Course course1 : course) {
            if (today.compareTo(LocalDate.parse(course1.getStartDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"))) > -1 && today.compareTo(LocalDate.parse(course1.getEndDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"))) < 0) {
                deleteNotAllowMap.put(course1.getCourseID(), true);
            } else {
                deleteNotAllowMap.put(course1.getCourseID(), false);
            }
        }

        String search = request.getParameter("searchInput").toLowerCase();
        List<Course> resultCourse = new ArrayList<>();
        for (Course course1 : course) {

            if (course1.getCourseID().toLowerCase().contains(search) || course1.getCourseName().toLowerCase().contains(search)) {
                Course c = course1;
                response.getWriter().print(111);
                resultCourse.add(c);
            }
        }

        request.setAttribute("deleteNotAllowMap", deleteNotAllowMap);
        request.setAttribute("course", resultCourse);

        request.getRequestDispatcher("manager-Homepage.jsp").forward(request, response);
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
