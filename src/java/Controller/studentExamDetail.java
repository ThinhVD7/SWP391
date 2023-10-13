/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
<<<<<<< HEAD
package Controller;

import Dal.DAO;
import Dal.LecturerDAO;
import Model.Account;
import Model.Exam;
import Model.Question;
import Model.StudentResult;
=======

package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Exam;
>>>>>>> b809fd8 (one half lecturer and std)
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import java.text.DecimalFormat;
import java.util.ArrayList;
=======
>>>>>>> b809fd8 (one half lecturer and std)

/**
 *
 * @author ROG
 */
public class studentExamDetail extends HttpServlet {
<<<<<<< HEAD

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
=======
   
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
            out.println("<title>Servlet studentExamDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet studentExamDetail at " + request.getContextPath () + "</h1>");
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
    throws ServletException, IOException 
    {
        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
>>>>>>> b809fd8 (one half lecturer and std)
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
<<<<<<< HEAD
        Account user = (Account) session.getAttribute("user");
        //check user's authority by role
        if (user.getRoleID() != 3) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        String examID = request.getParameter("examID");
        Exam thisExam = dao.loadAExam(examID);
        //session thisExam
        DAO d = new DAO();
        int numberOfQuestions = d.getNumberOfQuestion(Integer.parseInt(examID));
        boolean isDoQuizz = d.isDoQuiz(examID, user.getAccountID());
        if (isDoQuizz) {
            ArrayList<Question> questions = d.getQuestionsExam(Integer.parseInt(examID));
            float totalMarkAll = 0;
            for (Question question : questions) {
                totalMarkAll += question.getMark();
            }
            StudentResult studentResult = d.getResultStudent(examID, user.getAccountID());
//            df.setRoundingMode(RoundingMode.UP);
            request.setAttribute("totalMark", df.format((studentResult.getTotalScore() / totalMarkAll) * 10));
            request.setAttribute("totalTime", studentResult.getTotalTime());

        } else {
            request.setAttribute("timeLimit", dao.getStringFormattedDate("timeLimit", thisExam.getTimeLimit()));
            request.setAttribute("startDate", dao.getStringFormattedDate("dateTime", thisExam.getStartDate()));
            request.setAttribute("endDate", dao.getStringFormattedDate("dateTime", thisExam.getEndDate()));
            request.setAttribute("startDate1", thisExam.getStartDate());
            request.setAttribute("endDate1", thisExam.getEndDate());
            request.setAttribute("timeNow", java.time.LocalDateTime.now());
        }

        session.setAttribute("sessionThisExam", thisExam);
        request.setAttribute("isDoQuizz", isDoQuizz);
        request.setAttribute("examId", request.getParameter("examID"));

        request.getRequestDispatcher("studentExamDetail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
=======
        Account user = (Account)session.getAttribute("user");
        //check user's authority by role
        if(user.getRoleID()!=3)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        Exam thisExam = dao.loadAExam(request.getParameter("examID"));
        //session thisExam
        session.setAttribute("sessionThisExam", thisExam);
        
        
        request.setAttribute("timeLimit", dao.getStringFormattedDate("timeLimit", thisExam.getTimeLimit()));
        request.setAttribute("startDate", dao.getStringFormattedDate("dateTime", thisExam.getStartDate()));
        request.setAttribute("endDate", dao.getStringFormattedDate("dateTime", thisExam.getEndDate()));
        request.getRequestDispatcher("studentExamDetail.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
>>>>>>> b809fd8 (one half lecturer and std)
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        Account user = (Account) session.getAttribute("user");
        //check user's authority by role
        if (user.getRoleID() != 3) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ///////////////////////////////
        int examID = Integer.parseInt(request.getParameter("examID"));
        DAO d = new DAO();

        ArrayList<Question> questions = d.getQuestionsExam(examID);
        ArrayList<StudentResult> sResult = new ArrayList<>();
        for (Question question : questions) {
            ArrayList<Question> q = d.getChoice(Integer.parseInt(question.getQuestionID()));
            for (Question question1 : q) {
                if (question1.getAnswer() > 1) {
                    String[] type = request.getParameterValues("q" + question.getQuestionID());
//                    response.getWriter().println(type.length + " " + question1.getAnswer());
                    if (type != null) {
                        if (type.length != question1.getAnswer()) {
                        } else if (type.length == question1.getAnswer()) {
                            int mark = 0;
                            String choiceID = "";
                            for (String string : type) {
                                mark += Integer.parseInt(string.split(":")[0]);
                                if (choiceID.equals("")) {
                                    choiceID += string.split(":")[1];
                                } else {
                                    choiceID += ":" + string.split(":")[1];
                                }
                            }
                            question.setChoicePercentages(String.valueOf(mark));
                            question.setChoices(choiceID);
                        }

                    } else {
                        question.setChoicePercentages("0");
                        question.setChoices("");
                    }
                } else {
                    String qResult = "q" + question1.getQuestionID();
                    String result = request.getParameter(qResult);
                    if (result != null) {
                        question.setChoicePercentages(result.split(":")[0]);
                        question.setChoices(result.split(":")[1]);

                    }
                }
                break;
            }
        }

        float totalMark = 0;
        for (Question question : questions) {
            if (question.getChoicePercentages() != null && Integer.parseInt(question.getChoicePercentages()) > 0) {
                totalMark += (question.getMark() * Float.parseFloat(question.getChoicePercentages()))/100;
            }
        }
        float totalMarkAll = 0;
        for (Question question : questions) {
            totalMarkAll += question.getMark();
        }
        float TotalMarkIn10 = (totalMark / totalMarkAll) * 10;
        

        String hourElapse = request.getParameter("hourElapse");
        if (Integer.parseInt(hourElapse) < 10) {
            hourElapse = hourElapse;
        }
        String minuteElapse = request.getParameter("minuteElapse");
        if (Integer.parseInt(minuteElapse) < 10) {
            minuteElapse = minuteElapse;
        }
        String secondElapse = request.getParameter("secondElapse");
        if (Integer.parseInt(secondElapse) < 10) {
            secondElapse = secondElapse;
        }
        String totalTimeResult = hourElapse + ":" + minuteElapse + ":" + secondElapse;

        d.createResult(String.valueOf(examID), user.getAccountID(), totalMark, totalTimeResult, 0);
        boolean isDoQuizz = d.isDoQuiz(request.getParameter("examID"), user.getAccountID());
        request.setAttribute("isDoQuizz", isDoQuizz);
        request.setAttribute("totalMark", df.format(TotalMarkIn10));
//        request.setAttribute("totalMark", df.format(TotalMarkIn10));
        request.setAttribute("totalTime", totalTimeResult);
        request.setAttribute("isDoQuizz", isDoQuizz);
        request.setAttribute("examId", request.getParameter("examID"));
        request.getRequestDispatcher("studentExamDetail.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
=======
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
>>>>>>> b809fd8 (one half lecturer and std)
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

<<<<<<< HEAD
}
=======
}
>>>>>>> b809fd8 (one half lecturer and std)
