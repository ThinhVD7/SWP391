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
                        rs.getString(8));
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
                        rs.getString(8));
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
                        rs.getString(8));
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
                        rs.getString(8));
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
        public ArrayList<Lecturer> getlecturerByClass(String cID){
        try {
            ArrayList<Lecturer> list = new ArrayList<Lecturer>();
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join lecturer on account.Account_ID = lecturer.Lecturer_ID join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID where lecturerinwhichclass.Class_ID = ?;";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
             public ArrayList<Student> getstudentByClass(String cID){
        try {
            ArrayList<Student> list = new ArrayList<Student>();
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join student on "
                    + "account.Account_ID = student.Student_ID join studentinwhichclass on "
                    + "account.Account_ID = studentinwhichclass.Student_ID where studentinwhichclass.Class_ID=?;";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
            ResultSet rs = ps.executeQuery();
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
        }
        return null;
    }
     public List<Lecturer> getAllCourselecturer() {
        try {
            String strSelect ="select account.Account_ID, account.Name, account.Email from account join lecturer on account.Account_ID = lecturer.Lecturer_ID;";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lecturer c = new Lecturer();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                
                lecturer.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lecturer;
    }
         public List<Student> getAllCoursestudent() {
        try {
            String strSelect = "select account.Account_ID, account.Name, account.Email from account join student on account.Account_ID = student.Student_ID;";
            PreparedStatement ps = connector.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student c = new Student();
                c.setAccountID(rs.getString(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                
                student.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }
         
         public Course loadACourse(String courseID)
            {
                String sql = "SELECT * FROM course where Course_ID like ?";
                try
                     {
                         PreparedStatement ps = connector.prepareStatement(sql);
                         ps.setString(1, courseID);

                         ResultSet rs = ps.executeQuery();
                         while(rs.next())
                             {
                                 return new Course(rs.getString(1), 
                                         rs.getString(2),
                                         rs.getString(3), 
                                         rs.getString(4), 
                                         rs.getString(5));
                             }
                     }
                 catch(Exception e)
                     {
                         status = "Error at load a course"+ e.getMessage();
                     }
                 return null;
            }
         
         public Class1 getClassByID(String cid){
              String sql = "SELECT * FROM class where Class_ID = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(1);
                Class1 c = new Class1(rs.getString(1), rs.getString(2), rs.getString(3));
                return c;
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
         }
         
        
    public void insetLecturerIntoClass(String cID, String lID){
        try {
            String strSelect = "INSERT INTO lecturerinwhichclass (Lecturer_ID, Class_ID) VALUES (?, ?);";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
             ps.setString(2, lID);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
        public void insetStudentIntoClass(String cID, String lID){
        try {
            String strSelect = "INSERT INTO studentinwhichclass (Student_ID, Class_ID) VALUES (?, ?);";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
             ps.setString(2, lID);
             ps.executeUpdate();
           
        } catch (SQLException e) {            
          System.out.println(e);
        }
    }
        public void AddClass(String cID, String lID,String bID){
        try {
            String strSelect = "INSERT INTO class (Class_ID, ClassName, Course_ID) VALUES (?, ?, ?);";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
             ps.setString(2, lID);
             ps.setString(3, bID);
            ps.executeUpdate();
        } catch (SQLException e) {            
          System.out.println(e);
        }
    }
        public void deleteClassbyID(String classID)
            {
                String deleteClass = "";
                try 
                {
                    deleteClass = "delete from lecturerinwhichclass where Class_ID = ?";
                    PreparedStatement ps = connector.prepareStatement(deleteClass);
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
                } 
                catch (Exception e) 
                {
                    System.out.println(e);
                }
            }
            public void deleteLecturer(String Lecturer_ID) {
        String sql = "delete from lecturerinwhichclass where Lecturer_ID = ?";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, Lecturer_ID);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
        public void deleteStudent(String Student_ID) {
        String sql = "delete from studentinwhichclass where Student_ID = ?";
        try {
            PreparedStatement st = connector.prepareStatement(sql);
            st.setString(1, Student_ID);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
         public void deleteCourse(String Course_ID) {
             
           String deleteCourse = "";
        try {
                    deleteCourse = "delete from lecturerinwhichclass where Class_ID = ?";
                    PreparedStatement ps = connector.prepareStatement(deleteCourse);
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
        }
    }
      public void updateClass(String cID, String lID,String bID){
        try {
            String strSelect = "UPDATE class SET ClassName = '?' WHERE Class_ID = '?' and Course_ID='?';";
            PreparedStatement ps = connector.prepareStatement(strSelect);
             ps.setString(1, cID);
             ps.setString(2, lID);
             ps.setString(3, bID);
            ps.executeUpdate();
        } catch (SQLException e) {            
          System.out.println(e);
        }
    }
//        public boolean updateClass( String Class_ID,String ClassName) {
//
//        String sql = "UPDATE class SET ClassName = ? WHERE Class_ID = ?;";
//        try {
//            PreparedStatement st = connector.prepareStatement(sql);
//            st.setString(1,ClassName);
//            st.setString(2,Class_ID);
//            st.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return false;
//    }     
    public static void main(String[] args) {
        ManagerDAO d = new ManagerDAO();
          d.deleteCourse("asda");
        d.insetStudentIntoClass("thinhvd_se_11111", "SE1744_MAS291");
//        System.out.println(d.addAccount("123", "dunggnguyen", email, password, 0, 0, 0, phno));

    }

}
