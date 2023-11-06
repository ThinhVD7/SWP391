/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Dal.LecturerDAO;
import Model.Account;
import Model.Exam;
import Model.Question;
import Model.StudentAnswer;
import Model.StudentResult;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author DMX
 */
public class reviewQuizz extends HttpServlet {

    private static final DecimalFormat df = new DecimalFormat("0.00");
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
            out.println("<title>Servlet reviewQuizz</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reviewQuizz at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        LecturerDAO dao = new LecturerDAO();
        String examID = request.getParameter("examID");
        Exam thisExam = dao.loadAExam(examID);
        //session thisExam
        DAO d = new DAO();
        String name = d.getExamName(Integer.parseInt(examID));
        boolean isDoQuizz = d.isDoQuiz(examID, user.getAccountID());
        if (isDoQuizz) {
            ArrayList<Question> questions = d.getQuestionsExam(Integer.parseInt(examID));
            float totalMarkAll = 0;
            for (Question question : questions) {
                totalMarkAll += question.getMark();
            }
            StudentResult studentResult = d.getResultStudent(examID, user.getAccountID());
            request.setAttribute("totalMark",  df.format((studentResult.getTotalScore() / totalMarkAll) * 10));
            request.setAttribute("totalTime", studentResult.getTotalTime());

        }
        ArrayList<Question> questions = d.getQuestionsExam(Integer.parseInt(examID));     
        ArrayList<StudentAnswer> sa = d.getStudentAnswer(Integer.parseInt(examID), user.accountID);
        session.setAttribute("sessionThisExam", thisExam);
        request.setAttribute("isDoQuizz", isDoQuizz);
        request.setAttribute("examID", request.getParameter("examID"));
        request.setAttribute("name", name);
        request.setAttribute("questions", questions);
        request.setAttribute("sa", sa);
        request.setAttribute("StudentID", user.accountID);
        request.getRequestDispatcher("reviewQuizz.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
