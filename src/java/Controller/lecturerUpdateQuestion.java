/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Bank;
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
public class lecturerUpdateQuestion extends HttpServlet {

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
        request.getRequestDispatcher("lecturerUpdateQuestion.jsp").forward(request, response);

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
        Account lecturer = (Account) session.getAttribute("user");

        Bank bank = dao.getBankByCourseId(request.getParameter("course_id"), lecturer.getAccountID());

//        if (!dao.doesQuestionExistInBank(qId, bank.getBankId())) {
//            dao.updateQuestion(qId, title, content, type, Float.parseFloat(mark));
//        } else {
//            dao.addQuestion(title, content, type, Float.parseFloat(mark));
//        }
        String rCount = request.getParameter("newDivCount");
        int count = Integer.parseInt(rCount);
        int checkScore = 0;
        String[] choiceContent = new String[count];
        String[] choiceScore = new String[count];
        String[] choiceId = new String[count];
        int in = 0;
        while (in < count) {
            String surveyOptionsParam = request.getParameter(in + "_survey_options[]");
            String scoreParam = request.getParameter(in + "_score");
            String choiceIdParam = request.getParameter(in + "_choiceId");

            if (surveyOptionsParam != null && scoreParam != null) {
                choiceContent[in] = surveyOptionsParam;
                try {
                    choiceScore[in] = (scoreParam);
                } catch (NumberFormatException e) {
                    // Handle parsing error if needed
                    // choiceScore[in] will be 0 if parsing fails
                }
                if (choiceIdParam != null) {
                    choiceId[in] = choiceIdParam;

                } else {
                    choiceId[in] = " ";
                }
            } else {
                // Handle missing parameters or take appropriate action
            }
            in++;
        }

//        for (int i = 0; i < count; i++) {
//            choiceContent[i] = request.getParameter(i + "_survey_options[]");
//            choiceScore[i] = request.getParameter(i + "_score");
//            choiceId[i] = request.getParameter(i + "_choiceId");
//        }
        for (int i = 0; i < count; i++) {
            try {
                checkScore += Integer.parseInt(choiceScore[i]);
            } catch (NumberFormatException e) {
                // Handle parsing error, e.g., set a default value or show an error message.
            }
        }
        Exam e = (Exam) session.getAttribute("exam");

        List<ChoiceQuestion> listChoice = dao.getChoiceOfQuestion(qId);
        if (!dao.doesQuestionExistInBank(qId, bank.getBankId())) { ////////////////////////////////////////////// if question does not existed in bank
            //update normally

            dao.updateQuestion(qId, title, content, type, Float.parseFloat(mark));
            float currentMaxScore = e.getMaxScore();
            float markChange = Float.parseFloat(mark) - q.getMark();
            float newScore = currentMaxScore + markChange;
//            float newScore = e.getMaxScore() + q.getMark();
            int number = e.getQuestionNumber();
            String questionNumber = Integer.toString(number);
            dao.updateExamScore(e.getExamID(), Float.toString(newScore), questionNumber);

            for (int i = 0; i < count; i++) {
                if (!choiceId[i].isEmpty()) {
                    dao.updateChoice(qId, choiceContent[i], choiceScore[i], choiceId[i]);
                } else {
                    dao.addChoice(qId, choiceContent[i], choiceScore[i]);
                }

            }

        } else {
            // if question existed in bank, so must create coppy question of this question, add to question table and link to exam
            dao.addQuestion(title, content, type, Float.parseFloat(mark));
            Question newQuestion = dao.getLastestQuestion();
            for (int i = 0; i < count; i++) {
                dao.addChoice(newQuestion.getQuestionID(), choiceContent[i], choiceScore[i]);
            }
            dao.deleteQuestionExam(qId, e.getExamID());
            dao.updateExamScore(e.getExamID(), String.valueOf(e.getMaxScore() - (dao.getAQuestion(qId).getMark())), String.valueOf(e.getQuestionNumber() - 1));

            dao.addBank(e.getExamID(), newQuestion.getQuestionID());
            float currentMaxScore = e.getMaxScore();
            float markChange = Float.parseFloat(mark) - q.getMark();
            float newScore = currentMaxScore + markChange;
//            float newScore = e.getMaxScore() + newQuestion.getMark();
            int number = e.getQuestionNumber() + 1;
            String questionNumber = Integer.toString(number);
            dao.updateExamScore(e.getExamID(), Float.toString(newScore), questionNumber);

        }

        session.setAttribute("exam", e);
        response.sendRedirect("lecturerEditExam?tId=" + e.getExamID());
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
