package Dal;

import Model.*;
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
public class LecturerDAO extends DBContext{
    
    Connection connector;
    public List<Account> accountList = new ArrayList<>();
    public List<Student> studentList = new ArrayList<>();
    public List<Lecturer> lecturerList = new ArrayList<>();
    public List<Course> courseList = new ArrayList<>();
    public HashMap<String, Course> courseHashMap = new HashMap<>();
    public List<Class1> classList = new ArrayList<>();
    private String status = "yes";
    public String test;

    public LecturerDAO() 
    {
        try 
        {
            connector = new DBContext().getConnection();
            System.out.println("Connected");
            test = "Connected";
        } 
        catch (Exception e) 
        {
            System.out.println("Not connected");
            test = " Not Connected";
        }
    }
    
    public List<Course> loadAllCourses(String lecturerID)
             {
                 String sql = "SELECT * FROM course INNER JOIN (select distinct Course_ID from class join lecturerinwhichclass on class.Class_ID = lecturerinwhichclass.Class_ID where Lecturer_ID = ?) AS Subquery ON course.Course_ID = Subquery.Course_ID";
                 try
                     {
                         PreparedStatement ps = connector.prepareStatement(sql);
                         ps.setString(1, lecturerID);
                         
                         ResultSet rs = ps.executeQuery();
                         while(rs.next())
                             {
                                 System.out.println("");
                                 Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                                 courseList.add(c);
                             }
                         System.out.println("Ran");
                     }
                 catch(Exception e)
                     {
                         status = "Error at load Depart "+ e.getMessage();
                     }
                 return courseList;
             }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
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
    
    public List<Course> getAllCourse() {
        try {
            String strSelect = "select * from course";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("");
                Course c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                courseList.add(c);
            }
            System.out.println("Ran2");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courseList;
    }
    
    
    
    public static void main(String[] args) 
    {
        LecturerDAO dao = new LecturerDAO();
        //dao.loadAllCourses("minhvnt_he_176043");
       // List<Course> list2 = dao.getAllCourse();
        List<Course> list = dao.loadAllCourses("minhvnt_he_176043");
        if(list ==null)
            System.out.println("null");
        for (int i = 0; i < list.size(); i++) 
        {
            System.out.println(list.get(i).getCourseID());
            
        }
        Account acc = dao.getUser("nampthe171400@fpt.edu.vn");
        System.out.println(acc.getEmail());
        
    }
    
}
