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
            out.println("<title>Servlet managerViewLecturer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managerViewLecturer at " + request.getContextPath() + "</h1>");
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
        String cid = request.getParameter("CID");
        String courseID = request.getParameter("courseID");
//        check user's authority by role
        if (user.getRoleID() != 1) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ManagerDAO dao = new ManagerDAO();
        List<Lecturer> lecturer = dao.getlecturerByClass(cid);
        for (Lecturer lecturer1 : lecturer) 
        {
            
        }
        List<Student> student = dao.getstudentByClass(cid);
        List<Lecturer> addlecturer = dao.getAllCourselecturer();
        List<Student> addstudent = dao.getAllCoursestudent();
        List<Student> result = new ArrayList<>();
        for (Student student1 : addstudent) {
            boolean status = true;
            for (Student student2 : student) {
                if (student1.getAccountID().equals(student2.getAccountID())) {
                    status = true;
                    break;
                } else {
                    status = false;
                }
            }
            if (status == false) {
                result.add(student1);
            }
        }

        Class1 classInformation = dao.getClassByID(cid);
        request.setAttribute("cid", cid);
        request.setAttribute("lecturer", lecturer);
        request.setAttribute("enableAdd", lecturer.size());
        request.setAttribute("student", student);
        request.setAttribute("addlecturer", addlecturer);
        request.setAttribute("addstudent", addstudent);
        request.setAttribute("courseID", courseID);
        request.setAttribute("classInfo", classInformation);
        request.setAttribute("classID", cid);
        request.setAttribute("resultAddStudent", result);
        request.getRequestDispatcher("manager-ClassDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lecturerAdd = request.getParameter("add");
        String studentAdd = request.getParameter("add");
        String classID = request.getParameter("classID");
        String courseID = request.getParameter("courseID");
        String cid = request.getParameter("cid");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account) session.getAttribute("user");
        if (user.getRoleID() != 1) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ManagerDAO dao = new ManagerDAO();
        if (status == 1) {
            dao.insetLecturerIntoClass(lecturerAdd, classID);
        } else {
            dao.insetStudentIntoClass(studentAdd, classID);
        }

        List<Lecturer> lecturer = dao.getlecturerByClass(classID);
        List<Student> student = dao.getstudentByClass(classID);
        List<Lecturer> addlecturer = dao.getAllCourselecturer();
        List<Student> addstudent = dao.getAllCoursestudent();
        List<Student> result = new ArrayList<>();
        for (Student student1 : addstudent) {
            boolean status1 = true;
            for (Student student2 : student) {
                if (student1.getAccountID().equals(student2.getAccountID())) {
                    status1 = true;
                    break;
                } else {
                    status1 = false;
                }
            }
            if (status1 == false) {
                result.add(student1);
            }
        }

        Class1 classInformation = dao.getClassByID(classID);
        request.setAttribute("resultAddStudent", result);
        request.setAttribute("lecturer", lecturer);
        request.setAttribute("numberLecturer", lecturer.size());
        request.setAttribute("student", student);
        request.setAttribute("addlecturer", addlecturer);
        request.setAttribute("addstudent", addstudent);
        request.setAttribute("courseID", courseID);
        request.setAttribute("classInfo", classInformation);
        request.setAttribute("classID", classID);

        // response.getWriter().print(courseID + " " + lecturerAdd);
        //request.getRequestDispatcher("manager-ClassDetail.jsp").forward(request, response);
        response.sendRedirect("managerViewLecturer?CID="+cid+"&courseID="+courseID);
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
