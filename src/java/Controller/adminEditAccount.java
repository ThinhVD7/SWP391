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
 * @author ROG
 */
public class adminEditAccount extends HttpServlet {
   
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
            out.println("<title>Servlet adminEditAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminEditAccount at " + request.getContextPath () + "</h1>");
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
        //block check if user have logged in, if not then return to home
        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("user") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        //check active status
        Account user = (Account)session.getAttribute("user");
        if(user.getStatus()==0)
            {
                session.removeAttribute("user");
                request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        //check user's authority by role
        if(user.getRoleID()!=0)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ////////////////////////////////////////////////////////////////
        DAO dao = new DAO();
        Account target = dao.getUserById(request.getParameter("targetID"));
        //set target account
        session.setAttribute("targetAccount", target);
        request.setAttribute("id", target.getAccountID());
        request.setAttribute("name", target.getName());
        request.setAttribute("gender", target.getGender());
        request.setAttribute("email", target.getEmail());
        request.setAttribute("phno", target.getPhoneNumber());
        request.setAttribute("status", target.getStatus());
        request.setAttribute("role", target.getRoleID());
        session.removeAttribute("idErr");
        session.removeAttribute("nameErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("emailErr");
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
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
        Account target = (Account)session.getAttribute("targetAccount");
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
        String emailErr = valid.editEmailValid(email, target.getEmail());
        String idErr = "";
        if (dao.getUserById(id) != null && !id.equals(target.getAccountID())) {
            idErr = "ID existed!";
        }
        if (!nameErr.isEmpty() || !phoneErr.isEmpty() || !emailErr.isEmpty() || !idErr.isEmpty()) 
        {
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

        } 
        else 
        {
<<<<<<< HEAD
<<<<<<< HEAD
            String isSuccess = dao.updateAccount(target.getAccountID(), id, name, email, Integer.parseInt(role), Integer.parseInt(status), Integer.parseInt(gender), phno);
            if (isSuccess.equals("success")) 
=======
            boolean isSuccess = dao.updateAccount(target.getAccountID(), id, name, email, Integer.parseInt(role), Integer.parseInt(status), Integer.parseInt(gender), phno);
            if (isSuccess) 
>>>>>>> b809fd8 (one half lecturer and std)
=======
            String isSuccess = dao.updateAccount(target.getAccountID(), id, name, email, Integer.parseInt(role), Integer.parseInt(status), Integer.parseInt(gender), phno);
            if (isSuccess.equals("success")) 
>>>>>>> 3830c74 (update lecturer/student)
            {
                sendMail(email, 0);
                session.removeAttribute("idErr");
                session.removeAttribute("nameErr");
                session.removeAttribute("phoneErr");
                session.removeAttribute("emailErr");
                request.setAttribute("mess1", "Edit account successful. Notice Email sent!");
                session.setAttribute("targetAccount", dao.getUserById(id));
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.setAttribute("gender", gender);
                request.setAttribute("email", email);
                request.setAttribute("phno", phno);
                request.setAttribute("status", status);
                request.setAttribute("role", role);
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
<<<<<<< HEAD
<<<<<<< HEAD
            else
=======
            else 
>>>>>>> b809fd8 (one half lecturer and std)
=======
            else
>>>>>>> 3830c74 (update lecturer/student)
            {
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.setAttribute("gender", gender);
                request.setAttribute("email", email);
                request.setAttribute("phno", phno);
                request.setAttribute("status", status);
                request.setAttribute("role", role);
<<<<<<< HEAD
<<<<<<< HEAD
                if(isSuccess.contains("already"))
                    request.setAttribute("mess", "This account already attended a course that was in session!");
=======
>>>>>>> b809fd8 (one half lecturer and std)
=======
                if(isSuccess.contains("already"))
                    request.setAttribute("mess", "This account already attended a course that was in session!");
>>>>>>> 3830c74 (update lecturer/student)
                request.setAttribute("mess", "Edit account unsuccessfully. Please try again!");
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
                if(otpvalue ==0)
                {
                    message.setText("The admin has played with your account, please check your account information");
                }
                else
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
