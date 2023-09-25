package Controller;

import Dal.DAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ROG
 */
public class Login extends HttpServlet {
   
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         DAO dao = new DAO();
         Account acc =dao.getAccountLogin(username);
         
        if(acc==null)
            {
              request.setAttribute("loginfail", "Wrong username or password! Put your glasses on then try again!");
              request.getRequestDispatcher("Login.jsp").forward(request, response); 
            } 
        else
            {
           
        
            
                        //        if(username.compareTo("nampthe171400@fpt.edu.vn") ==0)
                        //            {
                        //        try (PrintWriter out = response.getWriter()) {
                        //            /* TODO output your page here. You may use following sample code. */
                        //            out.println("<!DOCTYPE html>");
                        //            out.println("<html>");
                        //            out.println("<head>");
                        //            out.println("<title>Servlet Login</title>");  
                        //            out.println("</head>");
                        //            out.println("<body>");
                        //            out.println("<h1>Servlet Login at " + dao.test + "</h1>");
                        //            out.println("</body>");
                        //            out.println("</html>");
                        //            }
                        //        
                        //        }
                        //        else 
                        //        {
                        //               request.setAttribute("loginfail", username.compareTo("nampthe171400@fpt.edu.vn"));
                        //            request.getRequestDispatcher("Login.jsp").forward(request, response); 
                        //                }
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
        processRequest(request, response);
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
