package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Class1;
import Model.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ROG
 */
public class lecturerExamList extends HttpServlet {
   
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
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        //check active status
        Account user = (Account)session.getAttribute("user");
        if(user.getStatus()==0)
            {
                session.removeAttribute("user");
                request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        Class1 thisClass = dao.loadAClass(request.getParameter("classID"));
        //sessionThisClass
        session.setAttribute("sessionThisClass", thisClass);
        
        List<Exam> examList = dao.loadAllExamofClass(thisClass.getClassID());
        
        HashMap<String, String> exam_startDate = new HashMap<String,String>();
        HashMap<String, String> exam_endDate = new HashMap<String,String>();
        String[] temp;
        for (Exam exam : examList) 
        {
            temp = exam.getStartDate().split("T");
            exam_startDate.put(exam.getExamID(),dao.getStringFormattedDate("date", temp[0]));
            temp = exam.getEndDate().split("T");
            exam_endDate.put(exam.getExamID(),dao.getStringFormattedDate("date", temp[0]));
        }
        request.setAttribute("lecturer", dao.loadALecturerofClass(thisClass.getClassID()));
        request.setAttribute("studentList", dao.loadStudentListofClass(thisClass.getClassID()));
        request.setAttribute("examList", examList);
        request.setAttribute("examStartDate", exam_startDate);
        request.setAttribute("examEndDate", exam_endDate);
        request.getRequestDispatcher("lecturerExamList.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
