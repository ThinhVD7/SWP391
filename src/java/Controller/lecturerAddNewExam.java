/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Class1;
import Model.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author tanki
 */
public class lecturerAddNewExam extends HttpServlet {

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
            out.println("<title>Servlet lecturerAddNewExam</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lecturerAddNewExam at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account)session.getAttribute("user");
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        session.removeAttribute("exam");
        request.getRequestDispatcher("lecturerAddExam.jsp").forward(request, response);
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
//        processRequest(request, response);

        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account)session.getAttribute("user");
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        session.removeAttribute("exam");

        String examName = request.getParameter("examName");
        
        ////temporary timeLimit convert/////////////////////////////////
        String timeLimitHour = request.getParameter("timeLimitHour");
        String timeLimitMinute = request.getParameter("timeLimitMinute");
        String timeLimitSecond = request.getParameter("timeLimitSecond");
        int hour = Integer.parseInt(timeLimitHour);
        int minute = Integer.parseInt(timeLimitMinute);
        int second = Integer.parseInt(timeLimitSecond);
        timeLimitHour = (hour<10)?("0" + String.valueOf(hour)):(String.valueOf(hour));
        timeLimitMinute = (minute<10)?("0" + String.valueOf(minute)):(String.valueOf(minute));
        timeLimitSecond = (second<10)?("0" + String.valueOf(second)):(String.valueOf(second));
        String timeLimit = timeLimitHour+":"+timeLimitMinute+":"+timeLimitSecond;
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String permission = request.getParameter("permission");
        String examDetail = request.getParameter("examDetail");
        Class1 thisClass = (Class1) session.getAttribute("sessionThisClass");
        String classId = thisClass.getClassID();
        LecturerDAO dao = new LecturerDAO();
//        String examId = examName + '_' + classId;
        if (dao.addExam(classId, examName, String.valueOf(0), timeLimit, fromDate, toDate, examDetail, String.valueOf(0), Integer.parseInt(permission), ((Account)session.getAttribute("user")).getAccountID())) {
            Exam exam = dao.getLastExam();
            response.sendRedirect("lecturerEditExam?tId=" + exam.getExamID());

        } else {
            response.sendRedirect("pageNotFound");
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
