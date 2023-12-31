package Dal;

import Model.Account;
import Model.ChoiceQuestion;
import Model.Lecturer;
import Model.Student;
import Model.Course;
import Model.Class1;
import Model.Question;
import Model.StudentAnswer;
import Model.StudentResult;
import java.sql.Connection;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import org.apache.tomcat.util.codec.binary.Base64;

public class DAO extends DBContext {

    Connection connector;
    public List<Account> account = new ArrayList<>();
    public List<Student> student = new ArrayList<>();
    public List<Lecturer> lecturer = new ArrayList<>();
    public List<Course> course = new ArrayList<>();
    public List<Class1> classes = new ArrayList<>();
    private String status = "yes";
    public String test;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public DAO() {
        try {
            connector = new DBContext().getConnection();
            System.out.println("Connected");
            test = "Connected";
        } catch (Exception e) {
            System.out.println("Not connected");
            test = " Not Connected";
        }
    }

    //result get saved to database,
    //future comparing password to the database saved one have to be encoded to compare
    public static String encodeSHA1(String password) {
        String result = "";
        try {
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void loadStudent() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        student = new Vector();
        String sql = "select * from role";
        try {
            ps = connector.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

            }
        } catch (Exception e) {
            status = "Error at get Student " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Account> getAllAcount() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select * from account";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getString(8));
                account.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return account;
    }

    public Account getAccountLogin(String email, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from account where Email = ? and Password = ?";
        try {
            ps = connector.prepareStatement(sql);
            password = DAO.encodeSHA1(password);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Account getUser(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from account where Email = ?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Account getUserById(String id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from account where Account_ID = ?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void why() {
        try {
            if (connector != null) {
                System.out.println("Connected");
                DatabaseMetaData dm = (DatabaseMetaData) connector.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: "
                        + dm.getDatabaseProductName());
                System.out.println("Product version: "
                        + dm.getDatabaseProductVersion());
                PreparedStatement ps = connector.prepareStatement("select * from account");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(2));
                }
            }
        } catch (SQLException e) {

        }
    }

    public boolean resetPassword(Account user) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE account SET Password = ? WHERE Email = ?";
            ps = connector.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public List<Class1> getClass(String studentId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select a.Student_ID,a.Class_ID,b.ClassName,c.Semester,c.Course_ID from studentinwhichclass a join class b on \n"
                + "a.Class_ID = b.Class_ID \n"
                + "join course c on b.Course_ID = c.Course_ID where a.Student_ID = ?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(1);
                Class1 c = new Class1(rs.getString(2), rs.getString(3), rs.getString(5));
                classes.add(c);

            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return classes;
    }

    public List<Class1> getAllClass() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select * from class";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Class1 c = new Class1(rs.getString(1), rs.getString(2), rs.getString(3));
                classes.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return classes;
    }

    public List<Course> getAllCourse() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select * from course";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("");
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                course.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return course;
    }

    public List<Course> getAllCourseByLId(String lecId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select * from course";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                course.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return course;
    }

    public void changePassword(String pass, String user) {
        try {
            String sql = "UPDATE account SET Password = ? WHERE Email = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, user);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean addAccount(String id, String name, String email, String password, int role, int status, int gender, String phno) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO account\n"
                    + "(Account_ID,\n"
                    + "Name,\n"
                    + "Email,\n"
                    + "Password,\n"
                    + "Role_ID,\n"
                    + "Status,\n"
                    + "Gender,\n"
                    + "PhoneNumber)\n"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            ps = connector.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setInt(5, role);
            ps.setInt(6, status);
            ps.setInt(7, gender);
            ps.setString(8, phno);
            ps.executeUpdate();
            if (role == 3) {
                sql = "Insert into student (Student_ID, Major, SchoolYear) values (?,?,?)";
                ps = connector.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.executeUpdate();

            } else if (role == 2) {
                sql = "Insert into lecturer (Lecturer_ID, Department, MeetLink) values (?,?,?)";
                ps = connector.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public String updateAccount(String id, String newID, String name, String email, int role, int status, int gender, String phno) {
        PreparedStatement ps = null;
        String sql = "update account set Account_ID = ?,Name = ?,Email = ?,Role_ID = ?,Status = ?,Gender = ?,PhoneNumber = ?where Account_ID = ?";
        try {
            ps = connector.prepareStatement(sql);

            //update account        
            ps = connector.prepareStatement(sql);
            ps.setString(1, newID);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setInt(4, role);
            ps.setInt(5, status);
            ps.setInt(6, gender);
            ps.setString(7, phno);
            ps.setString(8, id);
            ps.executeUpdate();

            //insert into certain role tables
            if (role == 3) {
                sql = "Insert into student (Student_ID, Major, SchoolYear) values (?,?,?) ON DUPLICATE KEY UPDATE Major = ?, SchoolYear = ?";
                ps = connector.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.setString(4, null);
                ps.setString(5, null);
                ps.executeUpdate();
                return "success";
            } else if (role == 2) {
                sql = "Insert into lecturer (Lecturer_ID, Department, MeetLink) values (?,?,?) ON DUPLICATE KEY UPDATE Department = ?, MeetLink = ?";
                ps = connector.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.setString(4, null);
                ps.setString(5, null);
                ps.executeUpdate();
                return "success";
            }
            return "success";
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "failed";
    }

    public void deleteAccountbyID(String accountID) {
        PreparedStatement ps = null;
        String deleteAccount = "delete from account where Account_ID = ?";
//                String sqlCondition = "select Role_ID from account where Account_ID = ?";
//                int roleID = 0;
        try {
            ps = connector.prepareStatement(deleteAccount);
//                    ps.setString(1, accountID);
//                    ps.executeQuery();
//                    ResultSet rs = ps.executeQuery();
//                    while(rs.next())
//                        {
//                            roleID = rs.getInt(1);
//                        }
//                    //lecturer role
//                    if(roleID == 2)
//                        {
//                            deleteAccount = "delete from lecturerinwhichclass where Lecturer_ID = ?";
//                            ps = connector.prepareStatement(deleteAccount);
//                            ps.setString(1, accountID);
//                            ps.executeUpdate();
//                            deleteAccount = "delete from lecturer where Lecturer_ID = ?";
//                            ps = connector.prepareStatement(deleteAccount);
//                            ps.setString(1, accountID);
//                            ps.executeUpdate();
//                        }
//                    //student role
//                    if(roleID == 3)
//                    {
//                        
//                        deleteAccount = "delete from studentinwhichclass where Student_ID = ?";
//                        ps = connector.prepareStatement(deleteAccount);
//                        ps.setString(1, accountID);
//                        ps.executeUpdate();
//                        deleteAccount = "delete from student where Student_ID = ?";
//                        ps = connector.prepareStatement(deleteAccount);
//                        ps.setString(1, accountID);
//                        ps.executeUpdate();
//                    }
            deleteAccount = "delete from account where Account_ID = ?";
            ps = connector.prepareStatement(deleteAccount);
            ps.setString(1, accountID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteClassbyID(String classID) {
        String deleteClass = "";
        PreparedStatement ps = null;
        try {
            deleteClass = "delete from lecturerinwhichclass where Class_ID = ?";
            ps = connector.prepareStatement(deleteClass);
            ps = connector.prepareStatement(deleteClass);
            ps.setString(1, classID);
            ps.executeUpdate();
            deleteClass = "delete from studentinwhichclass where Class_ID = ?";
            ps = connector.prepareStatement(deleteClass);
            ps.setString(1, classID);
            ps.executeUpdate();
            deleteClass = "delete from class where Class_ID = ?";
            ps = connector.prepareStatement(deleteClass);
            ps.setString(1, classID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteCoursebyID(String courseID) {
        String deleteCourse = "delete from course where Course_ID = ?";
        PreparedStatement ps = null;
        try {
            ps = connector.prepareStatement(deleteCourse);
            ps.setString(1, courseID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Question> getQuestionsExam(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT question.Question_ID, question.Title, question.QuestionContent, question.Type, question.Mark  FROM question as question\n"
                    + "INNER JOIN questioninwhichexam as qw\n"
                    + "WHERE question.Question_ID = qw.Question_ID AND Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            List<Question> questions = new ArrayList<>();
            rs = stm.executeQuery();
            int i = 1;
            while (rs.next()) {
                Question question = new Question();
                question.setAnswer(i);
                question.setQuestionID(rs.getString("Question_ID"));
                question.setTitle(rs.getString("Title"));
                question.setContent(rs.getString("QuestionContent"));
                question.setType(rs.getString("Type"));
                question.setMark(rs.getInt("Mark"));
                questions.add(question);
                i++;
            }
            return (ArrayList<Question>) questions;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return null;
    }

    public ArrayList<Question> getChoice(int questionID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Choice_ID, Question_ID, ChoiceContent,ScorePercentage FROM choicesofquestion WHERE Question_ID =  ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, questionID);
            ArrayList<Question> questions = new ArrayList<>();
            rs = stm.executeQuery();
            int status = 0;
            while (rs.next()) {
                Question question = new Question();
                question.setChoices(rs.getString("Choice_ID"));
                question.setQuestionID(rs.getString("Question_ID"));
                question.setChoicePercentages(rs.getString("ScorePercentage"));
                question.setContent(rs.getString("ChoiceContent"));
                if (rs.getInt("ScorePercentage") > 0) {
                    status++;
                }
                questions.add(question);
            }
            if (status > 1) {
                for (Question question : questions) {
                    question.setAnswer(status);
                }
            } else {
                for (Question question : questions) {
                    question.setAnswer(1);
                }
            }

            return questions;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean createResult(String ExamID, String StudentID, float totalMark, String totalTime, int state) {
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO studentresult VALUES (?, ?, ?, ?, ?, ?)";
            DAO dao = new DAO();
            String resultID = String.valueOf(dao.getNewResultID());
            stm = connector.prepareStatement(sql);
            stm.setString(1, resultID);
            stm.setString(2, ExamID);
            stm.setString(3, StudentID);
            stm.setFloat(4, totalMark);
            stm.setString(5, totalTime);
            stm.setInt(6, state);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int getNewResultID() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Result_ID FROM studentresult order by Result_ID DESC\n";
            stm = connector.prepareStatement(sql);
            rs = stm.executeQuery();
            int ResultID = 0;
            while (rs.next()) {
                if (ResultID < rs.getInt("Result_ID")) {
                    ResultID = rs.getInt("Result_ID");
                }
            }
            return ResultID + 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public int getNewAnswerID() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT StudentAnswer_ID FROM studentanswer order by StudentAnswer_ID DESC\n";
            stm = connector.prepareStatement(sql);
            rs = stm.executeQuery();
            int StudentAnswer_ID = 0;
            while (rs.next()) {
                if (StudentAnswer_ID < rs.getInt("StudentAnswer_ID")) {
                    StudentAnswer_ID = rs.getInt("StudentAnswer_ID");
                }
            }
            return StudentAnswer_ID + 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public boolean isDoQuiz(String examID, String studentID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM studentresult\n"
                    + "WHERE Exam_ID = ? AND Student_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setString(1, examID);
            stm.setString(2, studentID);
            rs = stm.executeQuery();
            int status = 0;
            while (rs.next()) {
                status++;
            }
            if (status >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return false;

    }

    public StudentResult getResultStudent(String examID, String StudentID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Exam_ID, Student_ID, TotalScore, TotalTime FROM studentresult\n"
                    + "WHERE Exam_ID = ? AND Student_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setString(1, examID);
            stm.setString(2, StudentID);
            rs = stm.executeQuery();
            StudentResult sr = new StudentResult();
            while (rs.next()) {
                sr.setExamID(rs.getString("Exam_ID"));
                sr.setStudentID(rs.getString("Student_ID"));
                sr.setTotalScore(rs.getFloat("TotalScore"));
                sr.setTotalTime(rs.getString("TotalTime"));
                break;
            }
            return sr;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return null;
    }

    public String getDetailExam(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TimeLimit FROM exam\n"
                    + "WHERE Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString("TimeLimit");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getExamName(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ExamName FROM exam\n"
                    + "WHERE Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString("ExamName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int getTotalMark(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.Choice_ID, c.ScorePercentage, q.Question_ID FROM choicesofquestion  as c\n"
                    + "INNER JOIN questioninwhichexam  as q \n"
                    + "WHERE c.Question_ID = q.Question_ID AND q.Exam_ID= ?";
            stm = connector.prepareStatement(sql);
            int total = 0;
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            while (rs.next()) {
                total += rs.getInt("ScorePercentage");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public int getNumberOfQuestion(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT QuestionNumber FROM exam\n"
                    + "WHERE Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("numberOfQueston");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return 0;
    }

    public int getNewStudentAnswer() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT StudentAnswer_ID, Question_ID, Choice_ID, Flag, Result_ID  FROM studentanswer order by StudentAnswer_ID DESC";
            stm = connector.prepareStatement(sql);
            rs = stm.executeQuery();
            int studentAnswer = 0;
            while (rs.next()) {
                if (studentAnswer < rs.getInt("StudentAnswer_ID")) {
                    studentAnswer = rs.getInt("StudentAnswer_ID");
                }
            }
            return studentAnswer + 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public boolean createStudentAnswer(int QuestionID, int ChoiceID, int ExamID, String StudentID) {
        PreparedStatement stm = null;

        try {
            String sql = "INSERT INTO studentanswer VALUES (?, ?, ?, ?, ?)";
            stm = connector.prepareStatement(sql);
            DAO dao = new DAO();
            String studentAnswer_ID = String.valueOf(dao.getNewAnswerID());
            stm.setString(1, studentAnswer_ID);
            stm.setInt(2, QuestionID);
            stm.setInt(3, ChoiceID);
            stm.setInt(4, ExamID);
            stm.setString(5, StudentID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int getLength(int examId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT e.Exam_ID, e.Class_ID, sc.Student_ID, ifnull(sr.TotalScore,0) as TotalScore FROM exam as e\n"
                    + "INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                    + "LEFT JOIN studentresult as sr on sr.Student_ID = sc.Student_ID\n"
                    + "WHERE e.Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examId);
            rs = stm.executeQuery();
            int length = 0;
            while (rs.next()) {
                length++;
            }
            return length;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public Float[] getResultByExamId(int examId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT e.Exam_ID, e.Class_ID, sc.Student_ID, ifnull(sr.TotalScore,0) as TotalScore FROM exam as e\n"
                    + "INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                    + "LEFT JOIN studentresult as sr on sr.Student_ID = sc.Student_ID\n"
                    + "WHERE e.Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examId);
            rs = stm.executeQuery();
            DAO d = new DAO();
            int size = d.getLength(examId);
            Float[] results = new Float[size];
            int index = 0;
            while (rs.next()) {
                float score = rs.getFloat("TotalScore");
                results[index] = score;
                index++;
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String[] getStudentsByExamId(int examId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT e.Exam_ID, e.Class_ID, sc.Student_ID, ifnull(sr.TotalScore,0) as TotalScore FROM exam as e\n"
                    + "INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                    + "LEFT JOIN studentresult as sr on sr.Student_ID = sc.Student_ID\n"
                    + "WHERE e.Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examId);
            rs = stm.executeQuery();
            DAO d = new DAO();
            int size = d.getLength(examId);
            String[] results = new String[size];
            int index = 0;
            while (rs.next()) {
                String score = rs.getString("Student_ID");
                results[index] = score;
                index++;
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<StudentResult> getStudentResult(int examId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select a.Exam_ID, a.Class_ID, a.Student_ID, ifnull(b2.TotalScore,0) as TotalScore from (\n"
                    + "	SELECT e.Exam_ID, e.Class_ID, sc.Student_ID  FROM exam as e\n"
                    + "	INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                    + "	WHERE e.Exam_ID = ?\n"
                    + ") as a\n"
                    + "LEFT JOIN (\n"
                    + "	SELECT * FROM studentresult where Exam_ID = ?\n"
                    + ") as b2 \n"
                    + " ON a.Student_ID = b2.Student_ID";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examId);
            stm.setInt(2, examId);
            DAO dao = new DAO();
            float examScore = dao.getExamScore(examId);

            rs = stm.executeQuery();
            ArrayList<StudentResult> results = new ArrayList<>();
            while (rs.next()) {
                StudentResult studentResult = new StudentResult();
                String studentId = rs.getString("Student_ID");
                float score = Float.parseFloat(df.format(rs.getFloat("TotalScore") / examScore * 10));
                studentResult.setStudentID(studentId);
                studentResult.setTotalScore(score);
                results.add(studentResult);
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

//    public ArrayList<StudentResult> getScoreStatistic(int examId) {
//        try {
//            String sql = "";
//            ArrayList<StudentResult> results = new ArrayList<>();
//            int min;
//            int max;
//            for (int i = 2; i <= 10; i += 2) {
//                min = i - 2;
//                max = i;
//                if (min == 0) {
//                    sql = "select Count(*)  as TotalScore from (\n"
//                            + "	SELECT e.Exam_ID, e.Class_ID, sc.Student_ID  FROM exam as e\n"
//                            + "	INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
//                            + "	WHERE e.Exam_ID = ?\n"
//                            + ") as a\n"
//                            + "LEFT JOIN (\n"
//                            + "	SELECT * FROM studentresult where Exam_ID = ?\n"
//                            + ") as b2 \n"
//                            + " ON a.Student_ID = b2.Student_ID\n"
//                            + "WHERE b2.TotalScore <=? or isnull(b2.TotalScore)";
//                    PreparedStatement stm = connector.prepareStatement(sql);
//                    stm.setInt(1, examId);
//                    stm.setInt(2, examId);
//                    stm.setInt(3, max);
//                    ResultSet rs = stm.executeQuery();
//                    while (rs.next()) {
//                        StudentResult studentResult = new StudentResult();
//                        studentResult.setResultID(min + "-" + max);
//                        studentResult.setState(rs.getInt("TotalScore"));
//                        results.add(studentResult);
//                    }
//                } else {
//                    sql = "select Count(*)  as Total from (\n"
//                            + "	SELECT e.Exam_ID, e.Class_ID, sc.Student_ID  FROM exam as e\n"
//                            + "	INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
//                            + "	WHERE e.Exam_ID = ?\n"
//                            + ") as a\n"
//                            + "LEFT JOIN (\n"
//                            + "	SELECT * FROM studentresult where Exam_ID = ?\n"
//                            + ") as b2 \n"
//                            + " ON a.Student_ID = b2.Student_ID\n"
//                            + " WHERE  b2.TotalScore >? and  b2.TotalScore <= ?\n"
//                            + "\n"
//                            + " ";
//                    PreparedStatement stm = connector.prepareStatement(sql);
//                    stm.setInt(1, examId);
//                    stm.setInt(2, examId);
//                    stm.setInt(3, min);
//                    stm.setInt(4, max);
//                    ResultSet rs = stm.executeQuery();
//                    while (rs.next()) {
//                        StudentResult studentResult = new StudentResult();
//                        studentResult.setResultID(min + "-" + max);
//                        studentResult.setState(rs.getInt("Total"));
//                        results.add(studentResult);
//                    }
//
//                }
//            }
//
//            return results;
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public ArrayList<StudentResult> getScoreStatistic(int examId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "";
            ArrayList<StudentResult> results = new ArrayList<>();
            int min;
            int max;
            int defaul = -1;
            for (int i = 0; i <= 10; i += 2) {
                min = i - 2;
                max = i;
                if (defaul == -1) {
                    sql = "select Count(*)  as TotalScore from (\n"
                            + "	SELECT e.Exam_ID, e.Class_ID, sc.Student_ID  FROM exam as e\n"
                            + "	INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                            + "	WHERE e.Exam_ID = ?\n"
                            + ") as a\n"
                            + "LEFT JOIN (\n"
                            + "	SELECT * FROM studentresult where Exam_ID = ?\n"
                            + ") as b2 \n"
                            + " ON a.Student_ID = b2.Student_ID\n"
                            + "WHERE isnull(b2.TotalScore)";
                    stm = connector.prepareStatement(sql);
                    stm.setInt(1, examId);
                    stm.setInt(2, examId);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        StudentResult studentResult = new StudentResult();
                        studentResult.setResultID("Not Submitted");
                        studentResult.setState(rs.getInt("TotalScore"));
                        results.add(studentResult);
                    }
                } else {
                    if (max == 2) {
                        sql = "select Count(*)  as Total from (\n"
                                + "                            SELECT e.Exam_ID, e.MaxScore, e.Class_ID, sc.Student_ID FROM exam as e\n"
                                + "                            INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                                + "                            WHERE e.Exam_ID = ?\n"
                                + "                            )as a\n"
                                + "                            LEFT JOIN (\n"
                                + "                            SELECT * FROM studentresult where Exam_ID = ?\n"
                                + "                            ) as b2\n"
                                + "                            ON a.Student_ID = b2.Student_ID\n"
                                + "                            WHERE b2.TotalScore/a.MaxScore*10>=? and b2.TotalScore/a.MaxScore*10<=?";
                        stm = connector.prepareStatement(sql);
                        stm.setInt(1, examId);
                        stm.setInt(2, examId);
                        stm.setInt(3, min);
                        stm.setInt(4, max);
                        rs = stm.executeQuery();
                        while (rs.next()) {
                            StudentResult studentResult = new StudentResult();
                            studentResult.setResultID(min + "-" + max);
                            studentResult.setState(rs.getInt("Total"));
                            results.add(studentResult);
                        }
                    } else {
                        sql = "select Count(*)  as Total from (\n"
                                + "                            SELECT e.Exam_ID, e.MaxScore, e.Class_ID, sc.Student_ID FROM exam as e\n"
                                + "                            INNER JOIN studentinwhichclass as sc on e.Class_ID =sc.Class_ID\n"
                                + "                            WHERE e.Exam_ID = ?\n"
                                + "                            )as a\n"
                                + "                            LEFT JOIN (\n"
                                + "                            SELECT * FROM studentresult where Exam_ID = ?\n"
                                + "                            ) as b2\n"
                                + "                            ON a.Student_ID = b2.Student_ID\n"
                                + "                            WHERE b2.TotalScore/a.MaxScore*10>? and b2.TotalScore/a.MaxScore*10<=?";
                        stm = connector.prepareStatement(sql);
                        stm.setInt(1, examId);
                        stm.setInt(2, examId);
                        stm.setInt(3, min);
                        stm.setInt(4, max);
                        rs = stm.executeQuery();
                        while (rs.next()) {
                            StudentResult studentResult = new StudentResult();
                            studentResult.setResultID(min + "-" + max);
                            studentResult.setState(rs.getInt("Total"));
                            results.add(studentResult);
                        }

                    }
                }

                defaul++;
            }

            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public float getExamScore(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MaxScore FROM exam\n"
                    + "WHERE Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 1;
    }

    public List<StudentResult> getAllStudentResultOfExam(int examID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Exam_ID, Student_ID, TotalScore, TotalTime FROM studentresult\n"
                    + "WHERE Exam_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, examID);
            rs = stm.executeQuery();
            List<StudentResult> srList = new ArrayList<>();
            DAO dao = new DAO();
            float examScore = dao.getExamScore(examID);
            float score = 0;
            while (rs.next()) {
                StudentResult sr = new StudentResult();

                sr.setExamID(rs.getString("Exam_ID"));
                sr.setStudentID(rs.getString("Student_ID"));
                score = Float.parseFloat(df.format(rs.getFloat("TotalScore") / examScore * 10));
                sr.setTotalScore(score);
                sr.setTotalTime(rs.getString("TotalTime"));
                srList.add(sr);
            }
            return srList;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean userCheck(String selectedChoiceID, String examID, String studentID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isChecked = false;

        try {
            stm = connector.prepareStatement("SELECT * FROM studentanswer WHERE Choice_ID = ? and Exam_ID = ? and Student_ID = ?");
            stm.setString(1, selectedChoiceID);
            stm.setString(2, examID);
            stm.setString(3, studentID);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (selectedChoiceID.equals(rs.getString("Choice_ID"))) {
                    isChecked = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Exception occurred while checking user answer", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return isChecked;
    }

    public ArrayList<StudentAnswer> getStudentAnswer(int Exam_ID, String Student_ID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM studentanswer WHERE Exam_ID = ? and Student_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, Exam_ID);
            stm.setString(2, Student_ID);
            rs = stm.executeQuery();
            ArrayList<StudentAnswer> answer = new ArrayList<>();
            while (rs.next()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                String studentId = rs.getString("Student_ID");
                int ExamID = rs.getInt("Exam_ID");
                int QuestionID = rs.getInt("Question_ID");
                String ChoiceID = rs.getString("Choice_ID");
                studentAnswer.setStudentID(studentId);
                studentAnswer.setQuestionID(QuestionID);
                studentAnswer.setChoiceID(ChoiceID);
                studentAnswer.setExamID(ExamID);
                answer.add(studentAnswer);
            }
            return answer;

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
        return null;
    }

    public void updateExamStatusActive(int examID) {
        String sql = "update exam set Status = 1 where Exam_ID = ?";
        PreparedStatement ps = null;
        try {
            ps = connector.prepareStatement(sql);
            ps.setInt(1, examID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public ArrayList<ChoiceQuestion> getChoiceOfExam(int Exam_ID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM  WHERE Exam_ID = ? and Student_ID = ?";
            stm = connector.prepareStatement(sql);
            stm.setInt(1, Exam_ID);
            rs = stm.executeQuery();
            ArrayList<ChoiceQuestion> answer = new ArrayList<>();
            while (rs.next()) {
                ChoiceQuestion choiceQuestion = new ChoiceQuestion();
                String studentId = rs.getString("Student_ID");
                int ExamID = rs.getInt("Exam_ID");
                int QuestionID = rs.getInt("Question_ID");
                String ChoiceID = rs.getString("Choice_ID");
//                choiceQuestion.setStudentID(studentId);
//                choiceQuestion.setQuestionID(QuestionID);
//                choiceQuestion.setChoiceID(ChoiceID);
//                choiceQuestion.setExamID(ExamID);
                answer.add(choiceQuestion);
            }
            return answer;

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
        return null;
    }

    public int getChoicePercentageOfStudentAnswer(String selectedChoiceID) {
        int choicePercentage = 0;

        try ( PreparedStatement stm = connector.prepareStatement("SELECT * FROM choicesofquestion WHERE Choice_ID = ?")) {
            stm.setString(1, selectedChoiceID);

            try ( ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (selectedChoiceID.equals(rs.getString("Choice_ID"))) {
                        int ScorePercentage = rs.getInt("ScorePercentage");
                        choicePercentage += ScorePercentage;
                        break;
                    }
                }

            }
        } catch (SQLException ex) {

        }

        return choicePercentage;
    }

    public double getPointEachQuestionOfStudentAnswer(String questionID, String studentID, String examId) {
//        DAO d = new DAO();
        double choicePercentage = 0;
        try ( PreparedStatement stm = connector.prepareStatement("SELECT * FROM studentanswer WHERE Question_ID = ? and Student_ID = ? and Exam_ID=?")) {
            stm.setString(1, questionID);
            stm.setString(2, studentID);
            stm.setString(3, examId);
            try ( ResultSet rs = stm.executeQuery()) {
                int a = 0;
                while (rs.next()) {
                    if (questionID.equals(rs.getString("Question_ID"))) {
                        choicePercentage += getChoicePercentageOfStudentAnswer(rs.getString("Choice_ID"));
                        a++;
                    }
                }
                if (a > getLengthOfCorrectChoice(questionID)) {
                    choicePercentage = 0;
                }
            } catch (Exception e) {

            }
        } catch (SQLException ex) {

        }
        return Double.parseDouble(df.format(choicePercentage));
    }

    public int getLengthOfCorrectChoice(String QuestionID) {
        int length = 0;
        try ( PreparedStatement stm = connector.prepareStatement("SELECT * FROM choicesofquestion WHERE Question_ID = ?")) {
            stm.setString(1, QuestionID);
            try ( ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (QuestionID.equals(rs.getString("Question_ID")) && rs.getInt("ScorePercentage") > 0) {
                        length++;
                    }
                }
            }
        } catch (SQLException ex) {

        }
        return length;
    }

    public String getPoint(String questionID, int questionMark, String examID, String studentID) {
//        DAO d = new DAO();
        double point = ((getPointEachQuestionOfStudentAnswer(questionID, studentID, examID) * questionMark) / 100) / (getExamScore(Integer.parseInt(examID))) * 10;
        return df.format(point);
    }

    public String getPoint2(int questionMark, String examID) {
        double point = (questionMark / getExamScore(Integer.parseInt(examID))) * 10;
        return df.format(point);
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        ArrayList<StudentResult> s = d.getScoreStatistic(1);
        for (StudentResult studentResult : s) {
            System.out.println(studentResult.getResultID() + " " + studentResult.getState());
        }

        System.out.println(d.getAllStudentResultOfExam(2).size());

////        System.out.println(d.getLength(1));
//        String[] f = d.getStudentsByExamId(1);
//        for (int i = 0; i < f.length; i++) {
//            System.out.println(f[i]);
//        }
//        System.out.println(d.getExamName(1));
//        String two = d.encodeSHA1("1691939");
//        d.getAllCourse();
//        System.out.println(two);
//        System.out.println(d.encodeSHA1("123456").equals(two));
//        System.out.println(d.addAccount("123", "dunggnguyen", email, password, 0, 0, 0, phno));
//        d.deleteAccountbyID("nampt_he_171400");
//        d.deleteClassbyID("yes");
//        d.deleteCoursebyID("MAS291");
        //System.out.println(d.addAccount("nampt_he_171400", two, two, two, 0, 3, 0, "12"));
        //System.out.println((d.getAccountLogin("nampthe171400@fpt.edu.vn", "1691939")!=null)?"success":"it null");
    }

}
