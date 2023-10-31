/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.StudentResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class lecturerStaticticExam1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();
        int examId = Integer.parseInt(request.getParameter("examId"));
         ArrayList<StudentResult> studentResult = d.getScoreStatistic(examId);
        String examName = d.getExamName(examId);
       
        request.setAttribute("studentResult", studentResult);
        request.setAttribute("examId", examId);
        request.setAttribute("examName", examName);
        request.getRequestDispatcher("lecturerStatisticExam1.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
