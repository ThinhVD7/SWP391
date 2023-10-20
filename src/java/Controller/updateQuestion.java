/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.ChoiceQuestion;
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
public class updateQuestion extends HttpServlet {

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
            out.println("<title>Servlet updateQuestion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateQuestion at " + request.getContextPath() + "</h1>");
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
        String qId = request.getParameter("questionId");
        Question q = dao.getQuestionById(qId);
        List<ChoiceQuestion> listChoice = dao.getChoiceOfQuestion(qId);
        request.setAttribute("question", q);
        request.setAttribute("listChoice", listChoice);
        session.setAttribute("sessionQuestion", q);
        request.getRequestDispatcher("updateQuestion.jsp").forward(request, response);

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
        Question q = (Question) session.getAttribute("sessionQuestion");
        String qId = q.getQuestionID();

        List<Question> list_Q = new ArrayList<>();
        LecturerDAO dao = new LecturerDAO();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("questionType");
        String mark = request.getParameter("mark");

        dao.updateQuestion(qId, title, content, type, Float.parseFloat(mark));
        

        String rCount = request.getParameter("newDivCount");
        int count = Integer.parseInt(rCount);
        int checkScore = 0;
        String[] choiceContent = new String[count];
        String[] choiceScore = new String[count];
        String[] choiceId = new String[count];

        for (int i = 0; i < count; i++) {
            choiceContent[i] = request.getParameter(i + "_survey_options[]");
            choiceScore[i] = request.getParameter(i + "_score");
            choiceId[i] = request.getParameter(i + "_choiceId");
        }
        for (int i = 0; i < count; i++) {
            checkScore += Integer.parseInt(choiceScore[i]);
        }

        for (int i = 0; i < count; i++) {
            dao.updateChoice(qId, choiceContent[i], choiceScore[i], choiceId[i]);
//            if (!dao.updateChoice(qId, choiceContent[i], choiceScore[i], choiceId[i]) || checkScore > 100) {
////                    request.setAttribute("err", "ERROR, Try again!");
//                response.sendRedirect("updateQuestion?questionId=" + qId);
//
//            }

        }
        Exam e = (Exam) session.getAttribute("exam");
        float newScore = e.getMaxScore() + q.getMark();
        int number = e.getQuestionNumber() + 1;
        String questionNumber = Integer.toString(number);
        dao.updateExamScore(e.getExamID(), Float.toString(newScore), questionNumber);
        session.setAttribute("exam", e);
        response.sendRedirect("editExam?tId=" + e.getExamID());
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
