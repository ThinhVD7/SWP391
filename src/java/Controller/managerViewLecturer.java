/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ManagerDAO;
import Model.Account;
import Model.Class1;
import Model.Lecturer;
import Model.Question;
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
        List<Lecturer> lecturer1 = dao.getlecturerByClassActive(cid);
        List<Lecturer> lecturer0 = dao.getlecturerByClassInactive(cid);
//        boolean condition = false;
        if (lecturer1.isEmpty()) {
            request.setAttribute("x", 1);

        }

//        int lecturerListSize = lecturer.size();
//        for (Lecturer lecturer1 : lecturer) {
//            if (dao.getUserById(lecturer1.getAccountID()).getStatus() == 0) {
//                lecturerListSize--;
//            }
//        }
        List<Student> student = dao.getstudentByClass(cid);
        List<Lecturer> addlecturer = dao.getAllCourselecturer();
        List<Lecturer> lecturerInThisClass = dao.getlecturerByClass(cid);

        if (lecturerInThisClass.isEmpty()) {
            request.setAttribute("y", 1);

        }

        List<Lecturer> addlecturerCoppy = new ArrayList<>(addlecturer);
        List<Lecturer> lecturerInThisClassCoppy = new ArrayList<>(lecturerInThisClass);

        for (Lecturer q : addlecturerCoppy) {
            for (Lecturer p : lecturerInThisClassCoppy) {
                if (q.getAccountID().equals(p.getAccountID())) {
                    addlecturer.remove(q); // Remove from the original list
                }
            }
        }

        List<Student> addstudent = dao.getAllCoursestudent();
        List<Student> addstudentCoppy = new ArrayList<>(addstudent);
        List<Student> studentCoppy = new ArrayList<>(student);
        for (Student q : addstudentCoppy) {
            for (Student p : studentCoppy) {
                if (q.getAccountID().equals(p.getAccountID())) {
                    addstudent.remove(q); // Remove from the original list
                }
            }
        }

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
        request.setAttribute("lecturer1", lecturer1);
        request.setAttribute("lecturer0", lecturer0);

//        request.setAttribute("enableAdd", lecturerListSize);
        request.setAttribute("student", student);

//        request.setAttribute("addlecturer", lecturer0);
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
        String[] studentAdd = request.getParameterValues("add");
        String classID = request.getParameter("classID");
        String courseID = request.getParameter("courseID");
        String cid = request.getParameter("cid");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        ManagerDAO dao = new ManagerDAO();

        ////////////////////////////////////////////////////////////////
        Account user = (Account) session.getAttribute("user");
        List<Lecturer> listL = dao.getlecturerByClass(classID);

        List<Lecturer> lecturerActive = dao.getlecturerByClassActive(classID);
        List<Lecturer> lecturer = dao.getlecturerByClassInactive(classID);
        boolean condition1 = false;
        boolean condition2 = false;
        int counting = 0;
        if (lecturerActive.isEmpty()) {
            condition1 = true;
        }
        if (listL.isEmpty()) {
            condition1 = true;
        }
        for (Lecturer x : listL) {
            if (x.getStatus() == 1) {
                counting++;
            }
        }

        if (user.getRoleID() != 1) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        if (status == 1) {
            if (counting < 1) {
                dao.insetLecturerIntoClass1(lecturerAdd, classID);

            }
            dao.insetLecturerIntoClass0(lecturerAdd, classID);

        } else {
            if (studentAdd != null) {
                for (int i = 0; i < studentAdd.length; i++) {
                    dao.insetStudentIntoClass(studentAdd[i], classID);
                }
            }else{
                response.sendRedirect("managerViewLecturer?CID=" + cid + "&courseID=" + courseID);
                return;
            }

        }

        int lecturerListSize = lecturer.size();
        for (Lecturer lecturer1 : lecturer) {
            if (dao.getUserById(lecturer1.getAccountID()).getStatus() == 0) {
                lecturerListSize--;
            }
        }
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
        request.setAttribute("lecturer", lecturerListSize);
        request.setAttribute("numberLecturer", lecturer.size());
        request.setAttribute("student", student);
        request.setAttribute("addlecturer", addlecturer);
        request.setAttribute("addstudent", addstudent);
        request.setAttribute("courseID", courseID);
        request.setAttribute("classInfo", classInformation);
        request.setAttribute("classID", classID);

        // response.getWriter().print(courseID + " " + lecturerAdd);
        //request.getRequestDispatcher("manager-ClassDetail.jsp").forward(request, response);
        response.sendRedirect("managerViewLecturer?CID=" + cid + "&courseID=" + courseID);
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
