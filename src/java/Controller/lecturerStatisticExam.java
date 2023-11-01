/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.StudentResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author admin
 */
public class lecturerStatisticExam extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int examId = Integer.parseInt(req.getParameter("examId"));
        DAO d = new DAO();
        ArrayList<StudentResult> studentResult = d.getStudentResult(examId);
        List<StudentResult> resultTable = d.getAllStudentResultOfExam(examId);
        String examName = d.getExamName(examId);
        
        req.setAttribute("resultTable", resultTable);
        req.setAttribute("studentResult", studentResult);
        req.setAttribute("examId", examId);
        req.setAttribute("examName", examName);
//        resp.getWriter().print(examId);
        req.getRequestDispatcher("lecturerStatisticExam.jsp").forward(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}
