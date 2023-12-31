/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Class1;
import Model.Exam;
import Model.Question;
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
 * @author tanki
 */
public class lecturerEditExam extends HttpServlet {

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
            out.println("<title>Servlet editExam</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editExam at " + request.getContextPath() + "</h1>");
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
        if(session.getAttribute("err")!=null) {
            session.removeAttribute("err");
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account)session.getAttribute("user");
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        String examID = request.getParameter("tId");
        Exam exam = dao.getExam(examID);
        session.setAttribute("exam", exam);
        session.setAttribute("examID", examID);
        request.setAttribute("examName", exam.getExamName());
        request.setAttribute("classID", exam.getClassID());
//        request.setAttribute("questionNumber", exam.getQuestionNumber());
        request.setAttribute("startDate", exam.getStartDate());
        request.setAttribute("endDate", exam.getEndDate());
        String[] temp = exam.getTimeLimit().split(":");
        int hour = Integer.parseInt(temp[0]);
        int minute = Integer.parseInt(temp[1]);
        int second = Integer.parseInt(temp[2]);
        request.setAttribute("timeLimitHour", hour);
        request.setAttribute("timeLimitMinute", minute);
        request.setAttribute("timeLimitSecond", second);
//        request.setAttribute("attemp", exam.getAttempsAllowed());
        request.setAttribute("examDetail", exam.getExamDetail());
        request.setAttribute("maxScore", exam.getMaxScore());
        request.setAttribute("questionNumber", exam.getQuestionNumber());
        request.setAttribute("permission", exam.getPermission());
        List<Question> listQ = dao.getListQuestionByExamID(examID);
        session.setAttribute("listQ", listQ);
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
        Exam exam = (Exam) session.getAttribute("exam");
        String examName = request.getParameter("examName");
        //String questionNumber = request.getParameter("questionNumber");
        //String maxScore = request.getParameter("maxScore");
        ////temporary timeLimit convert/////////////////////////////////
        String timeLimitHour = request.getParameter("timeLimitHour");
        String timeLimitMinute = request.getParameter("timeLimitMinute");
        String timeLimitSecond = request.getParameter("timeLimitSecond");
        int hour = Integer.parseInt(timeLimitHour);
        int minute = Integer.parseInt(timeLimitMinute);
        int second = Integer.parseInt(timeLimitSecond);
        timeLimitHour = (hour < 10) ? ("0" + String.valueOf(hour)) : (String.valueOf(hour));
        timeLimitMinute = (minute < 10) ? ("0" + String.valueOf(minute)) : (String.valueOf(minute));
        timeLimitSecond = (second < 10) ? ("0" + String.valueOf(second)) : (String.valueOf(second));
        String timeLimit = timeLimitHour + ":" + timeLimitMinute + ":" + timeLimitSecond;
        String fromDate = request.getParameter("fromDate");
        //String attemp = request.getParameter("attemp");
        String toDate = request.getParameter("toDate");
        String permission = request.getParameter("permission");
        String examDetail = request.getParameter("examDetail");
        Class1 thisClass = (Class1) session.getAttribute("sessionThisClass");
        String classId = thisClass.getClassID();
        LecturerDAO dao = new LecturerDAO();
        String examId = exam.getExamID();
        if (dao.updateExam(examId, classId, examName, timeLimit, fromDate, toDate, examDetail, Integer.parseInt(permission))) {
            response.sendRedirect("lecturerExamList?classID=" + classId);
            session.removeAttribute("exam");

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
