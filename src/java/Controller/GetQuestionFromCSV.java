/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Bank;
import Model.Course;
import Model.Exam;
import Model.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author nocol
 */
@WebServlet(name = "GetQuestionFromCSV", urlPatterns = {"/getQuestionCSV"})
public class GetQuestionFromCSV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;

        }
        String eId = request.getParameter("examID");
        String cId = request.getParameter("courseId");

        session.setAttribute("eId", eId);
        session.setAttribute("cId", cId);

        request.getRequestDispatcher("GetQuestionFromCSV.jsp").forward(request, response);

    }

    private final int COL_TITLE = 0;
    private final int COL_CONTENT = 1;
    private final int COL_TYPE = 2;
    private final int COL_MARK = 3;
    private final int COL_CHOICE = 4;
    private final int COL_ANSWER = 5;

    private final int MAX_COL = 6;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;

        }
        if (session.getAttribute("err") != null) {
            session.removeAttribute("err");
        }

        String cId = (String) session.getAttribute("cId");
        String eId = (String) session.getAttribute("eId");
        Account lecturer = (Account) session.getAttribute("user");
//        Exam thisEditExam = (Exam) request.getSession().getAttribute("sessionThisExam");
//        request.setAttribute("examID", thisEditExam.getExamID());
//        request.setAttribute("eId", thisEditExam.getExamID());
        LecturerDAO dbLecturer = new LecturerDAO();
//        Course course = (Course) request.getSession().getAttribute("sessionThisCourse");
        Bank b = dbLecturer.getBankByCourseId(cId, lecturer.getAccountID());

//        Course thisC = (Course) session.getAttribute("sessionThisCourse");
//        Account a = (Account) session.getAttribute("user");
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                String redirectURL = "getQuestionCSV?examID=" + eId + "&courseId=" + cId;

                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Set the temporary directory for storing the uploaded files
                String tempDir = System.getProperty("java.io.tmpdir");
                factory.setRepository(new File(tempDir));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request and retrieve the uploaded files
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

                // Process each uploaded file
                for (FileItem item : items) {
                    // Check if the current item is a file
                    if (!item.isFormField()) {
                        // Get the file name
                        String fileName = new File(item.getName()).getName();
                        System.out.println(fileName);
                        String projectPath = getServletContext().getRealPath("");
                        // Specify the directory to save the uploaded file
                        String uploadDir = projectPath + "..\\..\\web\\csv";

                        // Create the directory if it doesn't exist
                        File saveDir = new File(uploadDir);
                        if (!saveDir.exists()) {
                            saveDir.mkdirs();
                        }

                        // Save the file to the specified directory
                        String filePath = uploadDir + File.separator + fileName;
                        File uploadedFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(uploadedFile);

                        FileInputStream fis = new FileInputStream(uploadedFile);
                        XSSFWorkbook wb = new XSSFWorkbook(fis);
//creating a Sheet object to retrieve the object  
                        XSSFSheet sheet = wb.getSheetAt(0);
//evaluating cell type   
                        int index = 1;

                        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

                        Map<Integer, Integer> sizeRow = new HashMap<>();
                        boolean checkTitle = false;
                        boolean checkContent = false;
                        boolean checkType = false;
                        boolean checkMark = false;
                        boolean checkChoice = false;
                        boolean checkAnswer = false;

                        boolean floop = true;

                        for (Row row : sheet) {
                            int cnt = 0;
                            if (floop) {
                                for (Cell cell : row) {
                                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                                        case Cell.CELL_TYPE_STRING:
                                            String value = cell.getStringCellValue();
                                            if (value.equalsIgnoreCase("Title")) {
                                                checkTitle = true;
                                            }
                                            if (value.equalsIgnoreCase("Content")) {
                                                checkContent = true;
                                            }
                                            if (value.equalsIgnoreCase("Type")) {
                                                checkType = true;
                                            }
                                            if (value.equalsIgnoreCase("Mark")) {
                                                checkMark = true;
                                            }
                                            if (value.equalsIgnoreCase("Choice")) {
                                                checkChoice = true;
                                            }
                                            if (value.equalsIgnoreCase("Answer")) {
                                                checkAnswer = true;
                                            }
                                            break;
                                    }
                                    floop = false;
                                }
                            } else {
                                for (Cell cell : row) {
                                    cnt++;
                                }
                                sizeRow.put(row.getRowNum(), cnt);
                            }
                        }

//                        sizeRow.remove(12, 1);
                        boolean checkMaxCol = checkTitle && checkContent && checkType && checkMark && checkChoice && checkAnswer;
                        if (!checkMaxCol) {
//                            response.getWriter().println("Some headers is empty");

                            String error = "Some headers is empty";
                            session.setAttribute("err", error);
                            session.setAttribute("eId", eId);
                            session.setAttribute("cId", cId);
                            response.sendRedirect(redirectURL);
                            return;
                        }

                        for (Map.Entry<Integer, Integer> entry : sizeRow.entrySet()) {
                            Integer key = entry.getKey();
                            Integer val = entry.getValue();
                            System.out.println(key + "-" + val + "");
                            if (val != MAX_COL) {
                                String error = "Some cells in row " + (key + 1) + " is empty or overload";
                                session.setAttribute("err", error);
                                session.setAttribute("eId", eId);
                                session.setAttribute("cId", cId);
                                response.sendRedirect(redirectURL);
//                                response.getWriter().println("Some cells in " + (key + 1) + " is empty or overload");
                                return;
                            }
                        }

                        ArrayList<Question> questions = new ArrayList<>();
                        String error = "";
                        for (Row row : sheet) {
                            if (index > 1) {
                                int irow = 0;
                                Question question = new Question();
                                String[] choiceContent = new String[100];
                                String[] choicePercent = new String[100];
                                for (Cell cell : row) {
                                    System.out.println(formulaEvaluator.evaluateInCell(cell).getCellType());
                                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                                        case Cell.CELL_TYPE_NUMERIC:
                                            double valueDouble = cell.getNumericCellValue();
                                            int krow = irow % 6;
                                            switch (krow) {
                                                case COL_TITLE:
                                                    question.setTitle(String.valueOf(valueDouble));
                                                    break;
                                                case COL_CONTENT:
                                                    question.setContent(String.valueOf(valueDouble));
                                                    break;
                                                case COL_TYPE:
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    request.setAttribute("err", error);
                                                    request.getRequestDispatcher("GetQuestionFromCSV.jsp").forward(request, response);
                                                    return;
                                                case COL_MARK:
                                                    if (cell.getColumnIndex() != COL_MARK) {
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        return;
                                                    }

                                                    try {
                                                        float mark = (float) valueDouble;
                                                        if (mark < 0 || mark > 100) {
//                                                            response.getWriter().println("FILE MARK NEED IN [0,100] TYPE WRONG AT " + cell.getAddress().toString());
                                                            error = "FILE MARK NEED IN [0,100] TYPE WRONG AT " + cell.getAddress().toString();
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
                                                            return;
                                                        }
                                                        question.setMark(mark);
//                                                    dbLecturer.addQuestion(question.getTitle(), question.getContent(),
//                                                            question.getType(), question.getMark());

                                                        if (dbLecturer.doesQuestionExistInBankByTitleAndContent(question.getTitle(), question.getContent(), dbLecturer.getBankByCourseId(cId, lecturer.getAccountID()).getBankId())) {
//                                                            response.getWriter().println("Question already exists in bankon row number" + (row.getRowNum() + 1));
                                                            error = "Question already exists in bank on row number " + (row.getRowNum() + 1);
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
                                                            return;
                                                        }
                                                        for (Question q : questions) {
                                                            if (q.getContent().equals(question.getContent()) && q.getTitle().equals(question.getTitle())) {
//                                                                response.getWriter().println("Question is exists on row " + (row.getRowNum() + 1));
                                                                error = "Question is exists on row " + (row.getRowNum() + 1);
                                                                session.setAttribute("err", error);
                                                                session.setAttribute("eId", eId);
                                                                session.setAttribute("cId", cId);
                                                                response.sendRedirect(redirectURL);
                                                                return;
                                                            }
                                                        }
                                                        questions.add(question);
//                                                    Question lastestQuestion = dbLecturer.getLastestQuestion();
//                                                    dbLecturer.addToBank(b.getBankId(), lastestQuestion.getQuestionID());
                                                    } catch (Exception e) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    break;
                                                case COL_CHOICE:
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    session.setAttribute("err", error);
                                                    session.setAttribute("eId", eId);
                                                    session.setAttribute("cId", cId);
                                                    response.sendRedirect(redirectURL);
                                                    return;

                                                case COL_ANSWER:
//                                                    response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    session.setAttribute("err", error);
                                                    session.setAttribute("eId", eId);
                                                    session.setAttribute("cId", cId);
                                                    response.sendRedirect(redirectURL);
                                                    return;
//                                                    break;
                                                default:
                                                    irow--;
                                                    break;
                                            }
//                                            System.out.print(cell.getAddress().toString() + "-" + cell.getNumericCellValue() + "\t\t");
                                            break;

                                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                                            String value = cell.getStringCellValue();
                                            if (value == null || value.isEmpty()) {
//                                                response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                session.setAttribute("err", error);
                                                session.setAttribute("eId", eId);
                                                session.setAttribute("cId", cId);
                                                response.sendRedirect(redirectURL);
                                                return;
                                            }
                                            int jrow = irow % 6;
                                            switch (jrow) {
                                                case COL_TITLE:
                                                    if (cell.getColumnIndex() != COL_TITLE) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    question.setTitle(value);
                                                    break;
                                                case COL_CONTENT:
                                                    if (cell.getColumnIndex() != COL_CONTENT) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;

                                                    }
                                                    question.setContent(value);
                                                    break;
                                                case COL_TYPE:
                                                    if (cell.getColumnIndex() != COL_TYPE) {
//                                                        response.getWriter().println(" FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;

                                                    }
                                                    if (value.equalsIgnoreCase("Single") || value.equalsIgnoreCase("Multi")) {
                                                        question.setType(value.equalsIgnoreCase("Single") ? "0" : "1");
                                                    } else {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;

                                                    }
                                                    break;
                                                case COL_MARK:
                                                    if (cell.getColumnIndex() != COL_MARK) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    try {
                                                        float mark = Float.parseFloat(value);
                                                        question.setMark(mark);
                                                        if (dbLecturer.doesQuestionExistInBankByTitleAndContent(question.getTitle(), question.getContent(), dbLecturer.getBankByCourseId(cId, lecturer.getAccountID()).getBankId())) {

//                                                            response.getWriter().println("Question is exists on row " + row.getRowNum());
                                                            error = "Question is exists on row " + row.getRowNum();
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
                                                            return;
                                                        }
//                                                    dbLecturer.addQuestion(question.getTitle(), question.getContent(),
//                                                            question.getType(), question.getMark());
//                                                    Question lastestQuestion = dbLecturer.getLastestQuestion();
//                                                    dbLecturer.addToBank(b.getBankId(), lastestQuestion.getQuestionID());
                                                    } catch (Exception e) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;

                                                    }
                                                    break;
                                                case COL_CHOICE:
                                                    if (cell.getColumnIndex() != COL_CHOICE) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    choiceContent = value.split("\\|space\\|");
//                                                    System.out.println(value);
//                                                    for (String string : choiceContent) {
//                                                        System.out.println(string);
//                                                    }
                                                    break;

                                                case COL_ANSWER:
                                                    if (cell.getColumnIndex() != COL_ANSWER) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;

                                                    }
                                                    choicePercent = value.split("_");
                                                    try {
                                                        int sum = 0;
                                                        boolean check100 = false;
                                                        question.getType();
                                                        for (String string : choicePercent) {
                                                            int num = Integer.parseInt(string);
                                                            if (question.getType().equals("0") && check100 && num != 0) {
//                                                                response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());

                                                                error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                                session.setAttribute("err", error);
                                                                session.setAttribute("eId", eId);
                                                                session.setAttribute("cId", cId);
                                                                response.sendRedirect(redirectURL);
                                                                return;
                                                            }
                                                            if (question.getType().equals("0") && (num == 100)) {
                                                                check100 = true;
                                                            }
                                                            sum += Integer.parseInt(string);
                                                        }
                                                        if (question.getType().equals("0") && !check100) {
                                                            error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
//                                                            response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                            return;
                                                        }
                                                        if (sum != 100) {
//                                                            response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                            error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
                                                            return;

                                                        } else {
                                                            if (choiceContent.length != choicePercent.length) {
                                                                error = "CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                                session.setAttribute("err", error);
                                                                session.setAttribute("eId", eId);
                                                                session.setAttribute("cId", cId);
                                                                response.sendRedirect(redirectURL);
//                                                                response.getWriter().println("CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                                return;

                                                            }
                                                            Question lastestQuestion = dbLecturer.getLastestQuestion();
                                                            for (int i = 0; i < choiceContent.length; i++) {
                                                                for (int j = 0; j < choicePercent.length; j++) {
                                                                    if (i == j) {
                                                                        try {
                                                                            int real_choicePercent = Integer.parseInt(choicePercent[j]);
                                                                            if (real_choicePercent < 0) {
                                                                                error = "CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                                                session.setAttribute("err", error);
                                                                                session.setAttribute("eId", eId);
                                                                                session.setAttribute("cId", cId);
                                                                                response.sendRedirect(redirectURL);
//                                                                                response.getWriter().println("CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                                                return;
                                                                            }
                                                                        } catch (Exception e) {
                                                                        }
//                                                                        dbLecturer.addChoice(lastestQuestion.getQuestionID(), choiceContent[i], choicePercent[j]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception e) {
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString() + "-" + value.length());
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString() + "-" + value.length();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    break;

                                                default:
                                                    irow--;
                                                    break;
                                            }

//                                            System.out.print(cell.getAddress().toString() + "-" + cell.getStringCellValue() + "\t\t" + "-" + value.length());
                                            break;
                                    }
                                    irow++;
                                }
                            }
                            index++;
//                            System.out.println();
                        }

                        index = 1;
                        for (Row row : sheet) {
                            if (index > 1) {
                                int irow = 0;
                                Question question = new Question();
                                String[] choiceContent = new String[100];
                                String[] choicePercent = new String[100];
                                for (Cell cell : row) {
                                    System.out.println(formulaEvaluator.evaluateInCell(cell).getCellType());
                                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                                        case Cell.CELL_TYPE_NUMERIC:
                                            double valueDouble = cell.getNumericCellValue();
                                            int krow = irow % 6;
                                            switch (krow) {
                                                case COL_TITLE:
                                                    question.setTitle(String.valueOf(valueDouble));
                                                    break;
                                                case COL_CONTENT:
                                                    question.setContent(String.valueOf(valueDouble));
                                                    break;
                                                case COL_TYPE:
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    request.setAttribute("err", error);
                                                    request.getRequestDispatcher("GetQuestionFromCSV.jsp").forward(request, response);
                                                    return;
                                                case COL_MARK:
                                                    try {
                                                    float mark = (float) valueDouble;
                                                    if (mark < 0 || mark > 100) {
//                                                        response.getWriter().println("FILE MARK NEED IN [0,100] TYPE WRONG AT " + cell.getAddress().toString());
                                                        error = "FILE MARK NEED IN [0,100] TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
                                                        return;
                                                    }
                                                    question.setMark(mark);
                                                    if (dbLecturer.doesQuestionExistInBankByTitleAndContent(question.getTitle(), question.getContent(), dbLecturer.getBankByCourseId(cId, lecturer.getAccountID()).getBankId())) {

                                                        error = "Question is exists on " + (row.getRowNum() + 1) + ",FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
//                                                        response.getWriter().println("Question is exists on " + (row.getRowNum() + 1) + ",FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        return;
                                                    }
                                                    dbLecturer.addQuestion(question.getTitle(), question.getContent(),
                                                            question.getType(), question.getMark());
                                                    Question lastestQuestion = dbLecturer.getLastestQuestion();
                                                    dbLecturer.addToBank(b.getBankId(), lastestQuestion.getQuestionID());
                                                } catch (Exception e) {
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    session.setAttribute("err", error);
                                                    session.setAttribute("eId", eId);
                                                    session.setAttribute("cId", cId);
                                                    response.sendRedirect(redirectURL);
//                                                    response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                    return;
                                                }
                                                break;
                                                case COL_CHOICE:
                                                    break;

                                                case COL_ANSWER:
                                                    break;
                                                default:
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    session.setAttribute("err", error);
                                                    session.setAttribute("eId", eId);
                                                    session.setAttribute("cId", cId);
                                                    response.sendRedirect(redirectURL);
//                                                    response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                    break;
                                            }
//                                            System.out.print(cell.getAddress().toString() + "-" + cell.getNumericCellValue() + "\t\t");
                                            break;

                                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                                            String value = cell.getStringCellValue();
                                            if (value == null || value.isEmpty()) {
                                                error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                session.setAttribute("err", error);
                                                session.setAttribute("eId", eId);
                                                session.setAttribute("cId", cId);
                                                response.sendRedirect(redirectURL);
//                                                response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                return;
                                            }
                                            int jrow = irow % 6;
                                            switch (jrow) {
                                                case COL_TITLE:
                                                    question.setTitle(value);
                                                    break;
                                                case COL_CONTENT:
                                                    question.setContent(value);
                                                    break;
                                                case COL_TYPE:
                                                    if (value.equalsIgnoreCase("Single") || value.equalsIgnoreCase("Multi")) {
                                                        question.setType(value.equalsIgnoreCase("Single") ? "0" : "1");
                                                    } else {
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        return;

                                                    }
                                                    break;
                                                case COL_MARK:
                                                    try {
                                                    float mark = Float.parseFloat(value);
                                                    question.setMark(mark);
                                                    if (dbLecturer.doesQuestionExistInBankByTitleAndContent(question.getTitle(), question.getContent(), dbLecturer.getBankByCourseId(cId, lecturer.getAccountID()).getBankId())) {

                                                        error = "Question is exists on " + (row.getRowNum() + 1) + ",FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
//                                                        response.getWriter().println("Question is exists on " + (row.getRowNum() + 1) + ",FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                        return;
                                                    }
                                                    dbLecturer.addQuestion(question.getTitle(), question.getContent(),
                                                            question.getType(), question.getMark());
                                                    Question lastestQuestion = dbLecturer.getLastestQuestion();
                                                    dbLecturer.addToBank(b.getBankId(), lastestQuestion.getQuestionID());
                                                } catch (Exception e) {
                                                    error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                    session.setAttribute("err", error);
                                                    session.setAttribute("eId", eId);
                                                    session.setAttribute("cId", cId);
                                                    response.sendRedirect(redirectURL);
//                                                    response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                    return;

                                                }
                                                break;
                                                case COL_CHOICE:
                                                    choiceContent = value.split("\\|space\\|");
//                                                    System.out.println(value);
//                                                    for (String string : choiceContent) {
//                                                        System.out.println(string);
//                                                    }
                                                    break;

                                                case COL_ANSWER:
                                                    choicePercent = value.split("_");
                                                    try {
                                                        int sum = 0;
                                                        for (String string : choicePercent) {
                                                            sum += Integer.parseInt(string);
                                                        }
                                                        if (sum != 100) {
                                                            error = "FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                            session.setAttribute("err", error);
                                                            session.setAttribute("eId", eId);
                                                            session.setAttribute("cId", cId);
                                                            response.sendRedirect(redirectURL);
//                                                            response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                            return;

                                                        } else {
                                                            if (choiceContent.length < choicePercent.length) {
                                                                error = "CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                                session.setAttribute("err", error);
                                                                session.setAttribute("eId", eId);
                                                                session.setAttribute("cId", cId);
                                                                response.sendRedirect(redirectURL);
//                                                                response.getWriter().println("CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                                return;

                                                            }
                                                            Question lastestQuestion = dbLecturer.getLastestQuestion();
                                                            for (int i = 0; i < choiceContent.length; i++) {
                                                                for (int j = 0; j < choicePercent.length; j++) {
                                                                    if (i == j) {
                                                                        try {
                                                                            int real_choicePercent = Integer.parseInt(choicePercent[j]);
                                                                            if (real_choicePercent < 0) {
                                                                                error = "CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString();
                                                                                session.setAttribute("err", error);
                                                                                session.setAttribute("eId", eId);
                                                                                session.setAttribute("cId", cId);
                                                                                response.sendRedirect(redirectURL);
//                                                                                response.getWriter().println("CHOICE LENGTH NOT EQUAL CHOIC PERCENT ,FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                                                return;
                                                                            }
                                                                        } catch (Exception e) {
                                                                        }
                                                                        dbLecturer.addChoice(lastestQuestion.getQuestionID(), choiceContent[i], choicePercent[j]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        error = "FILE TYPE WRONG AT " + cell.getAddress().toString() + "-" + value.length();
                                                        session.setAttribute("err", error);
                                                        session.setAttribute("eId", eId);
                                                        session.setAttribute("cId", cId);
                                                        response.sendRedirect(redirectURL);
//                                                        response.getWriter().println("FILE TYPE WRONG AT " + cell.getAddress().toString() + "-" + value.length());
                                                        return;
                                                    }
                                                    break;

                                                default:
                                                    response.getWriter().println("NOT LOAD COLUMN,FILE TYPE WRONG AT " + cell.getAddress().toString());
                                                    break;
                                            }

//                                            System.out.print(cell.getAddress().toString() + "-" + cell.getStringCellValue() + "\t\t" + "-" + value.length());
                                            break;
                                    }
                                    irow++;
                                }
                            }
                            index++;
//                            System.out.println();
                        }
                        request.setAttribute("success", "File uploaded successfully");
//                             session.setAttribute("err", error);
                        session.setAttribute("eId", eId);
                        session.setAttribute("cId", cId);
                        response.sendRedirect(redirectURL);

                    }
                }
            } catch (FileUploadException e) {
                String redirectURL = "getQuestionCSV?examID=" + eId + "&courseId=" + cId;

                String error = "Error:" + e.getMessage();
//                request.setAttribute("err", error);
//                e.printStackTrace();
//                Exam thisEditExam = (Exam) request.getSession().getAttribute("sessionThisExam");
                session.setAttribute("err", error);
                session.setAttribute("eId", eId);
                session.setAttribute("cId", cId);
                response.sendRedirect(redirectURL);

//                response.getWriter().println("File upload failed!");
            } catch (Exception e) {
//                e.printStackTrace();
                String error = "Error:" + e.getMessage();

//                request.setAttribute("eId", eId);
//                request.setAttribute("cId", cId);
                session.setAttribute("err", error);
                session.setAttribute("eId", eId);
                session.setAttribute("cId", cId);
                String redirectURL = "getQuestionCSV?examID=" + eId + "&courseId=" + cId;
                response.sendRedirect(redirectURL);

//                request.getRequestDispatcher("GetQuestionFromCSV.jsp").forward(request, response);
            }
        } else {
            response.getWriter().println("Invalid request!");
        }
    }
}
