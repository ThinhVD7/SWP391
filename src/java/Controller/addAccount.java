/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tanki
 */
public class addAccount extends HttpServlet {

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
            out.println("<title>Servlet addAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addAccount at " + request.getContextPath() + "</h1>");
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
       //block check if user have logged in, if not then return to home
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        session.removeAttribute("idErr");
        session.removeAttribute("nameErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("emailErr");
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
        
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
            throws ServletException, IOException 
    {
        //block check if user have logged in, if not then return to home
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        ////////////////////////////////////////////////////////////////
        session.removeAttribute("idErr");
        session.removeAttribute("nameErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("emailErr");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phno = request.getParameter("phno");
        String status = request.getParameter("status");
        String role = request.getParameter("role");
        DAO dao = new DAO();
        validation valid = new validation();
        String nameErr = valid.nameValid(name);
        String phoneErr = valid.phoneValid(phno);
        String emailErr = valid.emailValid(email);
        String idErr = "";
        if (dao.getUserById(id) != null) {
            idErr = "Id existed!";
        }
        if (!nameErr.isEmpty() || !phoneErr.isEmpty() || !emailErr.isEmpty() || !idErr.isEmpty()) {
            session.setAttribute("idErr", idErr);
            session.setAttribute("nameErr", nameErr);
            session.setAttribute("phoneErr", phoneErr);
            session.setAttribute("emailErr", emailErr);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("gender", gender);
            request.setAttribute("email", email);
            request.setAttribute("phno", phno);
            request.setAttribute("status", status);
            request.setAttribute("role", role);
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);

        } else {
            int role1 = Integer.parseInt(role);
            int gender1 = Integer.parseInt(gender);
            int status1 = Integer.parseInt(status);

            int password = Random();
            sendMail(email, password);
            boolean isAddAccountSucess = dao.addAccount(id, name, email, Integer.toString(password), role1, status1, gender1, phno);
            if (isAddAccountSucess) {
                session.removeAttribute("idErr");
                session.removeAttribute("nameErr");
                session.removeAttribute("phoneErr");
                session.removeAttribute("emailErr");
                request.setAttribute("mess1", "Add Account success. Password sent!");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", "Cannot add account. Please try again!");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }

        }
    }

    public int Random() {
        int otpvalue = 0;
        Random rand = new Random();
        otpvalue = rand.nextInt(2222222);
        return otpvalue;
    }

    public void sendMail(String email, int otpvalue) {
//        String email = request.getParameter("email");
//        int otpvalue = 0;
//        HttpSession session = request.getSession();
        if (email != null || !email.equals("")) {
            // sending otp
//            Random rand = new Random();
//            otpvalue = rand.nextInt(2222222);

            String to = email;
            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session mysession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    //email , password of sender
                    return new PasswordAuthentication("otpbotswp@gmail.com", "wewttgnapgzfqmeq");
                }
            });
            // compose message
            try {
                MimeMessage message = new MimeMessage(mysession);
                message.setFrom(new InternetAddress(email));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // subject
                message.setSubject("Welcome!");
                // content
                message.setText("Your password is: " + otpvalue);
                // send message
                Transport.send(message);
                System.out.println("message sent successfully");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
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
