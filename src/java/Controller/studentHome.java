/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.Account;
import Model.Class1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
=======
import java.util.Enumeration;
>>>>>>> NamThanh
import java.util.List;
import java.util.Vector;
import javax.mail.Session;

/**
 *
 * @author tanki
 */
public class studentHome extends HttpServlet {

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
            out.println("<title>Servlet studentHome</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet studentHome at " + request.getContextPath() + "</h1>");
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
<<<<<<< HEAD
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("user");
        DAO dao = new DAO();
        List<Class1> c = dao.getClass(acc.accountID);
        request.setAttribute("user", acc);
        request.setAttribute("classes", c);
=======
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
        Account user = (Account)session.getAttribute("user");
        //check user's authority by role
        if(user.getRoleID()!=3)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        DAO dao = new DAO();
////test session block//////////////////////////////////////////////////////////////
//try (PrintWriter out = response.getWriter()) {
///* TODO output your page here. You may use following sample code. */
//out.println("<!DOCTYPE html>");
//out.println("<html>");
//out.println("<head>");
//out.println("<title>Servlet DemoSession03</title>");  
//out.println("</head>");
//out.println("<body>");
//out.print("<h1>SessionId: "+session.getId()+ "</h1>");
//Enumeration enu = session.getAttributeNames();
//while(enu.hasMoreElements())
//{
//    String key = enu.nextElement() + "";
//    Object value = session.getAttribute(key);
//    out.print("<h1> Attribute name = "+key+" : value = "+value);
//    out.print("<h2> Object:" +((Account)session.getAttribute("user")).getEmail()
//            +"role: " +((Account)session.getAttribute("user")).getRoleID()
//            +"</h2>");
//}
//out.println("</body>");
//out.println("</html>");
//}
////////////////////////////////////////////////////////////////////////////////////
        List<Class1> userClasses = dao.getClass(user.accountID);
        request.setAttribute("classes", userClasses);
>>>>>>> NamThanh
        request.getRequestDispatcher("student-homepage.jsp").forward(request, response);

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
