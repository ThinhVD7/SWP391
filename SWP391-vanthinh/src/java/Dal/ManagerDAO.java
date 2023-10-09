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

        String sql = "INSERT INTO `course` "
                + "(`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) \n"
                + "VALUES (?, ? , ? , ?, ?)";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, c.getCourseID());
            st.setString(2, c.getCourseName());
            st.setString(3, c.getSemester());
            st.setString(4, c.getStartDate());
            st.setString(5, c.getEndDate());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
        public boolean getCourseId(String Course_ID) {

        boolean dk = false;

        String sql = "select course.Course_ID "
                + "from course where course.Course_ID=?";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, Course_ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("Course_ID").equalsIgnoreCase(Course_ID)) {
                    dk = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dk;
    }

    public boolean updateInfo(Course a, String Course_ID) {

        String sql = "UPDATE `course`\n"
                + "SET\n"
                + "`Course_ID` = ?,\n"
                + "`CourseName` =?,\n"
                + "`Semester` = ?,\n"
                + "`StartDate` = ?,\n"
                + "`EndDate` = ?\n"
                + "WHERE `Course_ID` = ?;";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
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
        }
        return false;
    }

    public void deleteCourse(String Course_ID) {
        String sql = "DELETE FROM `course`\n"
                + "      WHERE `Course_ID` = ?;";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, Course_ID);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    //result get saved to database,
    //future comparing password to the database saved one have to be encoded to compare
    public String encodeSHA1(String password)
            {
                String result="";
                try
                    {
                        byte[] dataBytes = password.getBytes("UTF-8");
                        MessageDigest md = MessageDigest.getInstance("SHA-1");
                        result = Base64.encodeBase64String(md.digest(dataBytes));
                    }
                catch(Exception e)
                    {
                        status = "Error at encodeSHA1"+e.getMessage();
                    }
                return result;
            }

    public void loadStudent() {
        student = new Vector();
        String sql = "select * from role";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            }
        } catch (Exception e) {
            status = "Error at get Student " + e.getMessage();
        }
    }

    public List<Account> getAllAcount() {
        try {
            String strSelect = "select * from account";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getInt(8));
                account.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return account;
    }

    public Account getAccountLogin(String email, String password) {
        String sql = "select * from account where Email = ? and Password = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }

    public Account getUser(String email) {
        String sql = "select * from account where Email = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }

    public Account getUserById(String id) {
        String sql = "select * from account where Account_ID = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }


    public List<Class1> getClass(String studentId) {
        String sql = "select a.Student_ID,a.Class_ID,b.ClassName,c.Semester,c.Course_ID from studentinwhichclass a join class b on \n"
                + "a.Class_ID = b.Class_ID \n"
                + "join course c on b.Course_ID = c.Course_ID where a.Student_ID = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(1);
                Class1 c = new Class1(rs.getString(2), rs.getString(3), rs.getString(5));
                classes.add(c);

            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return classes;
    }

    public List<Class1> getAllClass() {
        try {
            String strSelect = "select * from class";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Class1 c = new Class1(rs.getString(1), rs.getString(2), rs.getString(3));
                classes.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return classes;
    }

    public List<Course> getAllCourse() {
        try {
            String strSelect = "select * from course";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                course.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return course;
    }

    public List<Course> getAllCourseByLId(String lecId) {
        try {
            String strSelect = "select * from course";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                course.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return course;
    }
    
    public ArrayList<Class1> getClassByCourseID(String cID){
        try {
            ArrayList<Class1> list = new ArrayList<Class1>();
            String strSelect = "SELECT * FROM class WHERE Course_ID = ?";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
            ResultSet rs = ps.executeQuery();
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
        }
        return null;
    }
    public static void main(String[] args) {
        ManagerDAO d = new ManagerDAO();
        String cid = "ACC101";
        ArrayList<Class1> list = d.getClassByCourseID(cid);
        for (Class1 class1 : list) {
            System.out.println(class1.toString());
        }
//        System.out.println(d.addAccount("123", "dunggnguyen", email, password, 0, 0, 0, phno));

    }

}
