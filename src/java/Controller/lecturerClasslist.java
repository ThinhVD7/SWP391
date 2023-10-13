package Controller;

import Dal.LecturerDAO;
import Model.Account;
import Model.Class1;
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
public class lecturerClasslist extends HttpServlet {
   
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
            out.println("<title>Servlet lecturerClasslist</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lecturerClasslist at " + request.getAttribute("courseID") + "</h1>");
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
    throws ServletException, IOException {
        //block check if user have logged in, if not then return to index
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b809fd8 (one half lecturer and std)
        //check active status
        Account user = (Account)session.getAttribute("user");
        if(user.getStatus()==0)
            {
                session.removeAttribute("user");
                request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
<<<<<<< HEAD
=======
        Account user = (Account)session.getAttribute("user");
>>>>>>> 1e16890 (yellow completed)
=======
>>>>>>> b809fd8 (one half lecturer and std)
        //check user's authority by role
        if(user.getRoleID()!=2)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        LecturerDAO dao = new LecturerDAO();
        String courseID = request.getParameter("courseID");
        //sessionThisCourse
        session.setAttribute("sessionThisCourse", dao.loadACourse(courseID));
        //sessionPageTitle temporary
        session.setAttribute("sessionPageTitle", dao.loadACourse(courseID).getCourseID()+" "+dao.loadACourse(courseID).getSemester());
        List<Class1> classList = dao.loadAllClassesofCourse(user.getAccountID(), courseID);
        HashMap<String, Integer> class_studentNumber = new HashMap<String,Integer>();
        HashMap<String, Integer> class_examNumber = new HashMap<String,Integer>();
        for (Class1 class1 : classList) 
        {
            class_studentNumber.put(class1.getClassID(), dao.numberofStudentofClass(class1.getClassID()));
            class_examNumber.put(class1.getClassID(), dao.numberofExamofClass(class1.getClassID()));
        }
<<<<<<< HEAD
        session.setAttribute("classListTemp", classList);
=======
>>>>>>> 1e16890 (yellow completed)
        request.setAttribute("studentNumber", class_studentNumber);
        request.setAttribute("examNumber", class_examNumber);
        request.setAttribute("classList", classList);
        request.getRequestDispatcher("lecturerClasslist.jsp").forward(request, response);
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
