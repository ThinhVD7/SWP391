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
import java.util.Enumeration;
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
public class Login extends HttpServlet {

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//    }

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
        //block check if user have logged in, if true then return to home
        HttpSession session = request.getSession(false);
        if(session ==null)
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        if(session.getAttribute("user") != null)
        {
            response.sendRedirect("home");
            return;
        }
        ////////////////////////////////////////////////////////////////
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        
        //block check if user have logged in, if true then return to home
        if(session.getAttribute("user") != null)
        {
            Account userCheck = (Account)session.getAttribute("user");
            if(userCheck.getStatus()==0)
                {
                    session.removeAttribute("user");
                    request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            response.sendRedirect("home");
            return;
        }
        ////////////////////////////////////////////////////////////////
        DAO dao = new DAO();
        Account acc = dao.getAccountLogin(username, password);

        if (acc == null) {
            request.setAttribute("mess", "Wrong username or password!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } 
        else 
        {
            if(acc.getStatus()==0)
                {
                    session.removeAttribute("user");
                    request.setAttribute("mess", "Your account has been suspended. Be nicer next time!");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
//            int otpvalue = Random();
//            sendMail(username, otpvalue);
//            request.getRequestDispatcher("OTP.jsp").forward(request, response);
//            session.setAttribute("otp", otpvalue);
//            session.setAttribute("email", username);
//            session.setAttribute("roleId", acc.roleID);
//            response.sendRedirect("OTP.jsp");
            request.getSession().setAttribute("user", acc);
            //      response.sendRedirect("indexStudent.jsp");

            int roleId = ((Account)session.getAttribute("user")).getRoleID();
////test session block//////////////////////////////////////////////////////////////
//try (PrintWriter out = response.getWriter()) 
//{
//out.println("<!DOCTYPE html>");
//out.println("<html>");
//out.println("<head>");
//out.println("<title>SessionDetail</title>");  
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
            response.sendRedirect("home");
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
