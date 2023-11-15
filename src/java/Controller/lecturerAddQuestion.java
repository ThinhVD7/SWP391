/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Bank;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tanki
 */
public class lecturerAddQuestion extends HttpServlet {

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
            out.println("<title>Servlet addQuestion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addQuestion at " + request.getAttribute("test") + "</h1>");
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
        request.getRequestDispatcher("lecturerAddNewExam").forward(request, response);
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
        List<Question> list_Q = new ArrayList<>();
        LecturerDAO dao = new LecturerDAO();

        if (session.getAttribute("exam") == null) { //neu chua tao exam thi tao exam truoc roi moi add question
            request.setAttribute("mess", "You must input all field !");
            request.getRequestDispatcher("lecturerAddExam.jsp").forward(request, response);
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("questionType");
        String mark = request.getParameter("mark");
        String addToBank = request.getParameter("addToBank");

        String[] choiceId = null;
//        LecturerDAO dao = new LecturerDAO();
//        try {

        if (dao.addQuestion(title, content, type, Float.parseFloat(mark))) {
            String rCount = request.getParameter("newDivCount");
            int count = Integer.parseInt(rCount);
            int checkScore = 0;
            String[] choiceContent = new String[count];
            String[] choiceScore = new String[count];
            Question q = dao.getLastestQuestion();

            for (int i = 0; i < count; i++) {
                choiceContent[i] = request.getParameter(i + "_survey_options[]");
                choiceScore[i] = request.getParameter(i + "_score");
            }
            for (int i = 0; i < count; i++) {
                checkScore += Integer.parseInt(choiceScore[i]);
            }

            for (int i = 0; i < count; i++) {
//                    request.setAttribute("test", count);
//                    request.setAttribute("test", String.valueOf(count) + " "+String.valueOf(i)+" "+String.valueOf(q.getQuestionID()) );
//                processRequest(request, response);
//                    dao.addChoice(q.getQuestionID(), choiceContent[i], choiceScore[i]);
//                    
                if (!dao.addChoice(q.getQuestionID(), choiceContent[i], choiceScore[i]) || checkScore > 100) {
                    request.setAttribute("err", "ERROR, Try again!");
                    Exam e = (Exam) session.getAttribute("exam");

                    response.sendRedirect("lecturerEditExam?tId=" + e.getExamID());

                }

            }
            if (addToBank != null) { //new checkbox addToBank duoc chon thi thuc hien add 1 question moi vao bank
                String courseID = request.getParameter("course_id");
                Account lecturer = (Account) session.getAttribute("user");
                String lecturerID = lecturer.getAccountID();
                if (dao.getBankByCourseId(courseID, lecturerID) == null) {
                    dao.addNewBank(courseID, lecturerID);
                }
                Bank bank = dao.getBankByCourseId(courseID, lecturerID);
                dao.addToBank(bank.getBankId(), q.getQuestionID());
//                dao.addToBank(mark, lecturerID)
            }

//                        request.setAttribute("test", "5");
//                processRequest(request, response);
            Exam e = (Exam) session.getAttribute("exam");
            if (dao.addBank(e.getExamID(), q.getQuestionID())) {
//                        request.setAttribute("test", "2");
//                    processRequest(request, response);
                float newScore = e.getMaxScore() + q.getMark();
                int number = e.getQuestionNumber() + 1;
                String questionNumber = Integer.toString(number);
                dao.updateExamScore(e.getExamID(), Float.toString(newScore), questionNumber);
                session.setAttribute("exam", e);
                response.sendRedirect("lecturerEditExam?tId=" + e.getExamID());
//                    request.getRequestDispatcher("addExam.jsp").forward(request, response);
            }

        } else {
//                        request.setAttribute("test", "3");
//                processRequest(request, response);
            request.setAttribute("EROR", "err123");
            request.getRequestDispatcher("lecturerAddNewExam").forward(request, response);

        }
//                        request.setAttribute("test", "4");
//                processRequest(request, response);

//        } catch (Exception e) {
//                        request.setAttribute("test", e);
//            processRequest(request, response);
//            e.printStackTrace();
//        }
        //1_survey_options[]
        //2_score
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
