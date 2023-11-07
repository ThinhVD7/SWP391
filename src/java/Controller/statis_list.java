public class Course {
    private String courseID;
    private String courseName;
    private String semester;
    private String startDate;
    private String endDate;

    public Course() {
    }

    public Course(String courseID, String courseName, String semester, String startDate, String endDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

public Exam(String examID, String classID, String examName, int questionNumber, String startDate, String endDate, String timeLimit, int attempsAllowed, String examDetail, float maxScore, int permission) {
        this.examID = examID;
        this.classID = classID;
        this.examName = examName;
        this.questionNumber = questionNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeLimit = timeLimit;
        this.attempsAllowed = attempsAllowed;
        this.examDetail = examDetail;
        this.maxScore = maxScore;
        this.permission = permission;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getAttempsAllowed() {
        return attempsAllowed;
    }
 public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
     public Class1(String classID, String className, String courseID) {
        this.classID = classID;
        this.className = className;
        this.courseID = courseID;
    }

    public String getClassID() {
        return classID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
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
        if(user.getRoleID()!=0)
            request.getRequestDispatcher("pageNotFound").forward(request, response);
        ///////////////////////////////
        
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
           
            session.removeAttribute("targetAccount");
            DAO dao = new DAO();
            List<Account> listA = dao.getAllAcount();
            request.setAttribute("listA", listA);
            request.getRequestDispatcher("admin-Homepage.jsp").forward(request, response);
        
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
        request.setAttribute("course", dao.loadAllCourses(user.getAccountID()));
        request.getRequestDispatcher("lecturer-homepage.jsp").forward(request, response);
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
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.html");
        } else {
            int role = (int) user.getRoleID();
            if (role == 0) {
                response.sendRedirect("admin");
            }
            if (role == 1) {
                response.sendRedirect("managerHome");
            }
            if (role == 2) {
                response.sendRedirect("lecturer");
            }
            if (role == 3) {
                response.sendRedirect("student");
            }
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
        processRequest(request, response);
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
        LecturerDAO daoTemp = new LecturerDAO();
        List<Class1> userClasses = dao.getClass(user.accountID);
        request.setAttribute("courseInfo", daoTemp.loadAllStudentCourse(user.accountID));
        request.setAttribute("classes", userClasses);
        request.getRequestDispatcher("student-homepage.jsp").forward(request, response);

    }