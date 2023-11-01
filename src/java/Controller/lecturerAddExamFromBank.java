/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Bank;
import Model.Exam;
import Model.Question;
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
 * @author tanki
 */
public class lecturerAddExamFromBank extends HttpServlet {

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
            out.println("<title>Servlet lecturerAddExamFromBank</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lecturerAddExamFromBank at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        LecturerDAO dao = new LecturerDAO();
        String courseID = request.getParameter("courseId");
        Account lecturer = (Account) session.getAttribute("user");

        Bank b = dao.getBankByCourseId(courseID, lecturer.getAccountID());
        List<Question> questionList = dao.getListQuestionByBank("3");
        String eId = request.getParameter("examID");
        request.setAttribute("eId", eId);
        List<Question> listQuestionOfThisExam = dao.getListQuestionByExamID(eId);

        List<Question> questionListCopy = new ArrayList<>(questionList); // Create a copy
        List<Question> questionListCopy2 = new ArrayList<>(listQuestionOfThisExam); // Create a copy

        for (Question q : questionListCopy) {
            for (Question p : questionListCopy2) {
                if (q.getQuestionID().equals(p.getQuestionID())) {
                    questionList.remove(q); // Remove from the original list
                }
            }
        }
        request.setAttribute("questionList", questionList);

//        String examID = request.getParameter("examID");
//        request.setAttribute("ex123", examID);
//            List<Question> listQuestionOfThisExam = dao.getListQuestionByExamID(eId);
//        request.setAttribute("listQuestionOld", listQuestionOfThisExam);
        request.getRequestDispatcher("lecturerAddExamFromBank.jsp").forward(request, response);
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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        LecturerDAO dao = new LecturerDAO();
        String examID = request.getParameter("eId");
        String r_count = request.getParameter("checkedCount");
        String c_count = request.getParameter("checkboxNumber");
        List<Question> listQuestionOfThisExam = dao.getListQuestionByExamID(examID);
        int ccount = Integer.parseInt(c_count);
        int count = Integer.parseInt(r_count);
        String[] listQ = new String[ccount];

        int in = 0;
        while (in < count) {
            for (int i = 0; i < ccount; i++) {
                if (request.getParameter("add_" + i) != null) {
                    listQ[in] = request.getParameter("add_" + i);
                    in++;

                }
            }
        }
        boolean status = false;
        for (String s : listQ) {
            Exam e = dao.getExam(examID);
            if (s != null) {
                if (dao.addBank(examID, s)) {
                    Question q = dao.getAQuestion(s);
                    float newScore = e.getMaxScore() + q.getMark();
                    int number = e.getQuestionNumber() + 1;
                    String questionNumber = Integer.toString(number);
                    dao.updateExamScore(e.getExamID(), Float.toString(newScore), questionNumber);
                    status = true;
                } else {
                    status = false;
                }
            }
            

        }
        if (status) {
            Exam e = dao.getExam(examID);

            session.setAttribute("exam", e);
            response.sendRedirect("lecturerEditExam?tId=" + e.getExamID());
        } else {

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
