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

public class DAO extends DBContext {

    Connection connector;
    public List<Account> account = new ArrayList<>();
    public List<Student> student = new ArrayList<>();
    public List<Lecturer> lecturer = new ArrayList<>();
    public List<Course> course = new ArrayList<>();
    public List<Class1> classes = new ArrayList<>();
    private String status = "yes";
    public String test;

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
        try {
            String sql = "UPDATE account SET Password = ? WHERE Email = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
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
                System.out.println("");
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

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setInt(5, role);
            ps.setInt(6, status);
            ps.setInt(7, gender);
            ps.setString(8, phno);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        String two = d.encodeSHA1("123456");
        d.getAllCourse();
        System.out.println(two);
        System.out.println(d.encodeSHA1("123456").equals(two));
//        System.out.println(d.addAccount("123", "dunggnguyen", email, password, 0, 0, 0, phno));

    }

}
