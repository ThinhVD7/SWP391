package Controller;

import Dal.LecturerDAO;
import Dal.ManagerDAO;
import Model.Account;
import Model.Class1;
import Model.Exam;
import Model.Lecturer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ROG
 */
public class lecturerExamList extends HttpServlet {

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
            out.println("<title>Servlet lecturerExamList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lecturerExamList at " + request.getParameter("classID") + "</h1>");
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
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        //check active status
        Account user = (Account) session.getAttribute("user");
        if (user.getStatus() == 0) {
            session.removeAttribute("user");
            request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        //check user's authority by role
        if (user.getRoleID() != 2) {
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        }
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        Class1 thisClass = dao.loadAClass(request.getParameter("classID"));
        //sessionThisClass
        session.setAttribute("sessionThisClass", thisClass);

        List<Exam> examList = dao.loadAllExamofClass(thisClass.getClassID());

        HashMap<String, String> exam_startDate = new HashMap<String, String>();
        HashMap<String, String> exam_endDate = new HashMap<String, String>();
        HashMap<String, Boolean> deleteNotAllowMap = new HashMap<String, Boolean>();
        //check different lecturer
        HashMap<String, Boolean> lecturerNotAuthorMap = new HashMap<String, Boolean>();
        LocalDateTime today = LocalDateTime.now();
        String[] temp;
        //today.compareTo(LocalDateTime.parse(exam.getStartDate()))>-1&&today.compareTo(LocalDateTime.parse(exam.getEndDate()))<0
        for (Exam exam : examList) {
            if (exam.getStatus() == 1 || !exam.getCreatedBy().equalsIgnoreCase(((Account) session.getAttribute("user")).getAccountID())) {
                deleteNotAllowMap.put(exam.getExamID(), true);
            } else {
                deleteNotAllowMap.put(exam.getExamID(), false);
            }
            temp = exam.getStartDate().split("T");
            exam_startDate.put(exam.getExamID(), dao.getStringFormattedDate("date", temp[0]));
            temp = exam.getEndDate().split("T");
            exam_endDate.put(exam.getExamID(), dao.getStringFormattedDate("date", temp[0]));
        }
        ManagerDAO mdao = new ManagerDAO();
        Account acc = (Account) session.getAttribute("user");
        Lecturer x = mdao.getLecturerExactly(acc.getAccountID(), request.getParameter("classID"));
        if (x.getStatus() == 1) {
            session.setAttribute("lecturerX", "abc");
        }

        request.setAttribute("lecturer", dao.loadALecturerofClass(thisClass.getClassID()));
        request.setAttribute("studentList", dao.loadStudentListofClass(thisClass.getClassID()));
        request.setAttribute("deleteNotAllowMap", deleteNotAllowMap);
        request.setAttribute("examList", examList);
        request.setAttribute("examStartDate", exam_startDate);
        request.setAttribute("examEndDate", exam_endDate);
        request.getRequestDispatcher("lecturerExamList.jsp").forward(request, response);
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
        processRequest(request, response);
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
