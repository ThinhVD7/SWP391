package Dal;

import Model.Account;
import Model.Lecturer;
import Model.Student;
import Model.Course;
import Model.Class1;
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
import org.apache.tomcat.util.codec.binary.Base64;

public class ManagerDAO extends DBContext {

    Connection connector;
    public List<Account> account = new ArrayList<>();
    public List<Student> student = new ArrayList<>();
    public List<Lecturer> lecturer = new ArrayList<>();
    public List<Course> course = new ArrayList<>();
    public List<Class1> classes = new ArrayList<>();

    private String status = "yes";
    public String test;

    public ManagerDAO() {
        try {
            connector = new DBContext().getConnection();
            System.out.println("Connected");
            test = "Connected";
        } catch (Exception e) {
            System.out.println("Not connected");
            test = " Not Connected";
        }
    }

    public boolean getCreateCouse(Course c) {
        PreparedStatement st = null;
        String sql = "INSERT INTO `course` "
                + "(`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) \n"
                + "VALUES (?, ? , ? , ?, ?)";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, c.getCourseID());
            st.setString(2, c.getCourseName());
            st.setString(3, c.getSemester());
            st.setString(4, c.getStartDate());
            st.setString(5, c.getEndDate());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean getCourseId(String Course_ID) {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean dk = false;

        String sql = "select course.Course_ID "
                + "from course where course.Course_ID=?";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, Course_ID);
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("Course_ID").equalsIgnoreCase(Course_ID)) {
                    dk = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dk;
    }

    public boolean updateInfo(Course a, String Course_ID) {
        PreparedStatement st = null;

        String sql = "UPDATE `course`\n"
                + "SET\n"
                + "`Course_ID` = ?,\n"
                + "`CourseName` =?,\n"
                + "`Semester` = ?,\n"
                + "`StartDate` = ?,\n"
                + "`EndDate` = ?\n"
                + "WHERE `Course_ID` = ?;";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, a.getCourseID());
            st.setString(2, a.getCourseName());
            st.setString(3, a.getSemester());
            st.setString(4, a.getStartDate());
            st.setString(5, a.getEndDate());
            st.setString(6, Course_ID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    //result get saved to database,
//    //future comparing password to the database saved one have to be encoded to compare
//    public String encodeSHA1(String password) {
//        String result = "";
//        try {
//            byte[] dataBytes = password.getBytes("UTF-8");
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            result = Base64.encodeBase64String(md.digest(dataBytes));
//        } catch (Exception e) {
//            status = "Error at encodeSHA1" + e.getMessage();
//        }
//        return result;
//    }
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Lecturer getLecturerExactly(String id, String classId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select account.Account_ID, account.Name, account.Email,lecturerinwhichclass.Status from account join lecturer on account.Account_ID = lecturer.Lecturer_ID join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID"
                + " where lecturerinwhichclass.Class_ID = ? AND Account_ID=?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, classId);
            ps.setString(2, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setStatus(rs.getInt(4));
                return c;
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                course.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
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
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return course;
    }

    public ArrayList<Class1> getClassByCourseID(String cID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Class1> list = new ArrayList<Class1>();
            String strSelect = "SELECT * FROM class WHERE Course_ID = ?";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Class1 c = new Class1();
                c.setClassID(rs.getString(1));
                c.setClassName(rs.getString(2));
                c.setCourseID(rs.getString(3));

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Lecturer> getlecturerByClass(String cID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Lecturer> list = new ArrayList<Lecturer>();
            String strSelect = "select account.Account_ID, account.Name, account.Email,lecturerinwhichclass.Status from account join lecturer on account.Account_ID = lecturer.Lecturer_ID join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID where lecturerinwhichclass.Class_ID = ?;";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setStatus(rs.getInt(4));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public ArrayList<Lecturer> getlecturerByClassInactive(String cID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Lecturer> list = new ArrayList<Lecturer>();
            String strSelect = "select account.Account_ID, account.Name, account.Email,lecturerinwhichclass.Status from account join lecturer on account.Account_ID = lecturer.Lecturer_ID join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID where lecturerinwhichclass.Class_ID = ? AND lecturerinwhichclass.Status = 0;";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setStatus(rs.getInt(4));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Lecturer> getlecturerByClassActive(String cID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Lecturer> list = new ArrayList<Lecturer>();
            String strSelect = "select account.Account_ID, account.Name, account.Email,lecturerinwhichclass.Status from account join lecturer on account.Account_ID = lecturer.Lecturer_ID join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID where lecturerinwhichclass.Class_ID = ? AND lecturerinwhichclass.Status = 1;";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setStatus(rs.getInt(4));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Student> getstudentByClass(String cID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Student> list = new ArrayList<Student>();
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join student on "
                    + "account.Account_ID = student.Student_ID join studentinwhichclass on "
                    + "account.Account_ID = studentinwhichclass.Student_ID where studentinwhichclass.Class_ID=?;";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student c = new Student();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Lecturer> getAllCourselecturer() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join lecturer on account.Account_ID = lecturer.Lecturer_ID;";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));

                lecturer.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecturer;
    }

    public List<Student> getAllCoursestudent() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join student on account.Account_ID = student.Student_ID;";
            ps = connector.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student c = new Student();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));

                student.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }

    public Course loadACourse(String courseID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM course where Course_ID like ?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, courseID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Course(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
            status = "Error at load a course" + e.getMessage();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Class1 getClassByID(String cid) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM class where Class_ID = ?";
        try {
            ps = connector.prepareStatement(sql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(1);
                Class1 c = new Class1(rs.getString(1), rs.getString(2), rs.getString(3));
                return c;
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void insetLecturerIntoClass0(String cID, String lID) {
        PreparedStatement ps = null;
        try {
            String strSelect = "INSERT INTO lecturerinwhichclass (Lecturer_ID, Class_ID,Status) VALUES (?, ?, ?);";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            ps.setString(2, lID);
            ps.setInt(3, 0);

            ps.executeUpdate();

        } catch (SQLException e) {
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

    public void insetLecturerIntoClass1(String cID, String lID) {
        PreparedStatement ps = null;
        try {
            String strSelect = "INSERT INTO lecturerinwhichclass (Lecturer_ID, Class_ID,Status) VALUES (?, ?,?);";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            ps.setString(2, lID);
            ps.setInt(3, 1);
            ps.executeUpdate();

        } catch (SQLException e) {
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

    public void insetStudentIntoClass(String cID, String lID) {
        PreparedStatement ps = null;
        try {
            String strSelect = "INSERT INTO studentinwhichclass (Student_ID, Class_ID) VALUES (?, ?);";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            ps.setString(2, lID);
            ps.executeUpdate();

        } catch (SQLException e) {
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

    public void AddClass(String cID, String lID, String bID) {
        PreparedStatement ps = null;
        try {
            String strSelect = "INSERT INTO class (Class_ID, ClassName, Course_ID) VALUES (?, ?, ?);";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            ps.setString(2, lID);
            ps.setString(3, bID);
            ps.executeUpdate();
        } catch (SQLException e) {
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
        PreparedStatement ps = null;
        String deleteClass = "";
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

    public void deleteLecturer(String Lecturer_ID, String Class_ID) {
        PreparedStatement ps = null;
        String sql = "delete from lecturerinwhichclass where Lecturer_ID = ? and Class_ID = ?";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, Lecturer_ID);
            st.setString(2, Class_ID);
            st.executeUpdate();
        } catch (SQLException e) {
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

    public void setLecturerStatus(String Lecturer_ID, String Class_ID, int Status) {
        PreparedStatement st = null;
        String sql = "update `lecturerinwhichclass` SET `Status` = ?  WHERE Lecturer_ID = ? and Class_ID = ?";
        try {
            st = connector.prepareStatement(sql);
            st.setInt(1, Status);
            st.setString(2, Lecturer_ID);
            st.setString(3, Class_ID);

            st.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteStudent(String Student_ID, String Class_ID) {
        PreparedStatement st = null;
        String sql = "delete from studentinwhichclass where Student_ID = ? AND Class_ID= ?";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, Student_ID);
            st.setString(2, Class_ID);
            st.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteCourse(String Course_ID) {
        PreparedStatement ps = null;
        String deleteCourse = "";
        try {
            deleteCourse = "delete from lecturerinwhichclass where Class_ID = ?";
            ps = connector.prepareStatement(deleteCourse);
            ps = connector.prepareStatement(deleteCourse);
            ps.setString(1, deleteCourse);
            ps.executeUpdate();
            deleteCourse = "delete from studentinwhichclass where Class_ID = ?";
            ps = connector.prepareStatement(deleteCourse);
            ps.setString(1, deleteCourse);
            ps.executeUpdate();
            deleteCourse = "delete from class where Class_ID = ?";
            ps = connector.prepareStatement(deleteCourse);
            ps.setString(1, deleteCourse);
            ps.executeUpdate();
            deleteCourse = "delete from course where Course_ID = ?";
            ps = connector.prepareStatement(deleteCourse);
            ps.setString(1, Course_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
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

    public void updateClass(String cID, String lID, String bID) {
        PreparedStatement ps = null;
        try {
            String strSelect = "UPDATE class SET ClassName = '?' WHERE Class_ID = '?' and Course_ID='?';";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, cID);
            ps.setString(2, lID);
            ps.setString(3, bID);
            ps.executeUpdate();
        } catch (SQLException e) {
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

    public boolean editClass(String Class_ID, String ClassName, String Course_ID) {
        PreparedStatement st = null;
        AddClass(ClassName + "_" + Course_ID, ClassName, Course_ID);
        editClass2(ClassName + "_" + Course_ID, Class_ID);
        editClass3(ClassName + "_" + Course_ID, Class_ID);
        String sql = "DELETE FROM `class` WHERE (`Class_ID` = ?);";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, Class_ID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean editClass2(String Class_ID1, String Class_ID2) {
        PreparedStatement st = null;
        String sql = "UPDATE `studentinwhichclass`\n"
                + "SET\n"
                + "`Class_ID` = ?\n"
                + "WHERE `Class_ID` = ?;";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, Class_ID1);
            st.setString(2, Class_ID2);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean editClass3(String Class_ID1, String Class_ID2) {
        PreparedStatement st = null;
        String sql = "UPDATE `lecturerinwhichclass`\n"
                + "SET\n"
                + "`Class_ID` = ?\n"
                + "WHERE `Class_ID` = ?;";
        try {
            st = connector.prepareStatement(sql);
            st.setString(1, Class_ID1);
            st.setString(2, Class_ID2);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkClassID(String ClassName, String Course_ID) {
        PreparedStatement st = null;
        try {
            String strSelect = "select class.Class_ID from class where class.ClassName = ? and class.Course_ID = ?;";
            st = connector.prepareStatement(strSelect);
            st.setString(1, ClassName);
            st.setString(2, Course_ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                rs.getString("Class_ID");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public Course getACourseById(String courseID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String strSelect = "select * from course where Course_ID = ?";
            ps = connector.prepareStatement(strSelect);
            ps.setString(1, courseID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return c;
            }
        } catch (SQLException e) {
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
        return null;
    }

    public boolean getClassNameExit(String className, String Course_ID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM class where ClassName = ? and Course_ID = ?";
        try {
             ps = connector.prepareStatement(sql);
            ps.setString(1, className);
            ps.setString(2, Course_ID);
             rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ManagerDAO d = new ManagerDAO();
        d.deleteCourse("asda");
        d.insetStudentIntoClass("thinhvd_se_11111", "SE1744_MAS291");
//        System.out.println(d.addAccount("123", "dunggnguyen", email, password, 0, 0, 0, phno));

    }

}
