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
 * @author DMX
 */
public class ForgotPassword extends HttpServlet {

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
//        processRequest(request, response);
        //block check if user have logged in, if true then return to home
        HttpSession session = request.getSession(false);
        if(session ==null)
            request.getRequestDispatcher("forget-password.jsp").forward(request, response);
        if(session.getAttribute("user") != null)
        {
            response.sendRedirect("home");
            return;
        }
        ////////////////////////////////////////////////////////////////
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);

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


        String username = request.getParameter("email");
        HttpSession session = request.getSession(false);
        //block check if user have logged in, if true then return to home
        if(session.getAttribute("user") != null)
        {
            response.sendRedirect("home");
            return;
        }
        ////////////////////////////////////////////////////////////////

        DAO dao = new DAO();
        Account acc = dao.getUser(username);

        if (acc == null) {
            request.setAttribute("mess", "Wrong email!");
            request.getRequestDispatcher("forget-password.jsp").forward(request, response);
        } else {
            

            if (username != null || !username.equals("")) {
                // sending otp
                Random rand = new Random();
                int otpvalue = Random();
                String pass = Integer.toString(otpvalue);
                String to = username;
                // Get the session object
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session mysession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //username , password of sender
                        return new PasswordAuthentication("otpbotswp@gmail.com", "wewttgnapgzfqmeq");
                    }
                });
                // compose message
                try {
                    MimeMessage message = new MimeMessage(mysession);
                    message.setFrom(new InternetAddress(username));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    // subject
                    message.setSubject("Forgot password!");
                    // content
                    message.setText("Your new password is: " + otpvalue);
                    // send message
                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                try {
                    acc.setEmail(username);
                    acc.setPassword(pass);
                    boolean resetPasswordSuccess = dao.resetPassword(acc);
                    if (resetPasswordSuccess) {
                        request.setAttribute("err", "Reset password successfully! \n Please check mail to get new password");
                        request.getRequestDispatcher("forget-password.jsp").forward(request, response);
                    } else {
                        request.setAttribute("err", "Cannot reset password. Please try again!");
                        request.getRequestDispatcher("forget-password.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                message.setSubject("Verify your account");
                // content
                message.setText("Your OTP is: " + otpvalue);
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
