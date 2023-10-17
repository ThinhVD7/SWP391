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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturerDAO extends DBContext {

    Connection connector;
    public List<Account> accountList = new ArrayList<>();
    public List<Student> studentList = new ArrayList<>();
    public List<Lecturer> lecturerList = new ArrayList<>();
    public List<Course> courseList = new ArrayList<>();
    public HashMap<String, Course> courseHashMap = new HashMap<>();
    public List<Class1> classList = new ArrayList<>();
    public List<Exam> examList = new ArrayList<>();
    private String status = "yes";
    public String test;

    public LecturerDAO() {
        try {
            connector = new DBContext().getConnection();
            System.out.println("Connected");
            test = "Connected";
        } catch (Exception e) {
            System.out.println("Not connected");
            test = " Not Connected";
        }
    }

    public Course loadACourse(String courseID) {
        String sql = "SELECT * FROM course where Course_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, courseID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Course(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return null;
    }

    public Class1 loadAClass(String classID) {
        String sql = "SELECT * FROM class where Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Class1(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return null;
    }

    public Exam loadAExam(String examID) {
        String sql = "SELECT * FROM exam where Exam_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, examID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Exam(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getInt(11));
            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return null;
    }

    public List<Course> loadAllCourses(String lecturerID) {
        String sql = "SELECT * FROM course INNER JOIN (select distinct Course_ID from class join lecturerinwhichclass on class.Class_ID = lecturerinwhichclass.Class_ID where Lecturer_ID = ?) AS Subquery ON course.Course_ID = Subquery.Course_ID";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, lecturerID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseList.add(new Course(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return courseList;
    }

    public List<Class1> loadAllClassesofCourse(String lecturerID, String courseID) {
        List<String> result = new ArrayList();
        String sql = "SELECT distinct class.Class_ID, class.ClassName, class.Course_ID FROM class join lecturerinwhichclass on lecturerinwhichclass.Lecturer_ID like ? where class.Course_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, lecturerID);
            ps.setString(2, courseID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classList.add(new Class1(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
        }
        return classList;
    }

    public List<Account> loadStudentListofClass(String classID) {
        List<String> result = new ArrayList();
        String sql = "select account.Account_ID, account.Name, account.Email from account join studentinwhichclass on account.Account_ID = studentinwhichclass.Student_ID where studentinwhichclass.Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accountList.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), ""));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
        }
        return accountList;
    }

    public int numberofStudentofClass(String classID) {
        int count = 0;
        String sql = "select student.Student_ID from student join studentinwhichclass on studentinwhichclass.Student_ID = student.Student_ID where Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            status = "Error at number student " + e.getMessage();
        }
        return count;
    }

    public int numberofExamofClass(String classID) {
        int count = 0;
        String sql = "select Exam_ID from exam where Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            status = "Error at number exam " + e.getMessage();
        }
        return count;
    }

    public List<Exam> loadAllExamofClass(String classID) {
        String sql = "SELECT * from exam where Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                examList.add(new Exam(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getInt(11)));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
        }
        return examList;
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

    //add exam
    public boolean addExam(String examId, String classId, String examName, String timeLimit, String startDate, String endDate, int permission) {
        try {
            String sql = "INSERT INTO exam\n"
                    + "(Exam_ID,\n"
                    + "Class_ID,\n"
                    + "ExamName,\n"
                    + "StartDate,\n"
                    + "EndDate,\n"
                    + "TimeLimit,\n"
                    + "Permission)\n"
                    + "VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, examId);
            ps.setString(2, classId);
            ps.setString(3, examName);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.setString(6, timeLimit);
            ps.setInt(7, permission);
            ps.executeUpdate();
             return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public static void main(String[] args) {
        LecturerDAO dao = new LecturerDAO();

        //test add exam
        dao.addExam("id1", "SE1732_MAS291", "PT2", "60", "2023-04-28T16:25:49.000", "2023-04-28T16:25:49.000", 1);

        //test user
        Account acc = dao.getUser("nampthe171400@fpt.edu.vn");
        System.out.println(acc.getEmail());

        //test load a course
        System.out.println(dao.loadACourse("JPD134").getCourseID() + " " + dao.loadACourse("JPD134").getSemester());

        //test load all course
        dao.loadAllCourses("minhvnt_he_176043");

        //test load all class of a course
        List<Class1> classList = dao.loadAllClassesofCourse("minhvnt_he_176043", "MAS291");
        for (int i = 0; i < classList.size(); i++) {
            System.out.println(classList.get(i).getClassName() + classList.get(i).getClassID());
        }

        //test load all courses of lecturer
        List<Course> list = dao.loadAllCourses("minhvnt_he_176043");
        if (list == null) {
            System.out.println("null");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCourseID());
        }

        //test load all exam of a class
        List<Exam> examList = dao.loadAllExamofClass("SE1732_MAS291");
        for (int i = 0; i < examList.size(); i++) {
            System.out.println(examList.get(i).getExamName() + examList.get(i).getClassID());
        }

        //test number of student
        System.out.println(dao.numberofStudentofClass("SE1732_MAS291"));

        //test number of exam
        System.out.println(dao.numberofExamofClass("SE1732_MAS291"));

        HashMap<String, Integer> class_studentNumber = new HashMap<String, Integer>();
        HashMap<String, Integer> class_examNumber = new HashMap<String, Integer>();
        for (Class1 class1 : classList) {
            class_studentNumber.put(class1.getClassID(), dao.numberofStudentofClass(class1.getClassID()));
            class_examNumber.put(class1.getClassID(), dao.numberofExamofClass(class1.getClassID()));
        }

        System.out.println(class_studentNumber);
        System.out.println(class_examNumber);

        LocalDateTime dateTime = LocalDateTime.now();
        //default format
        System.out.println("Default format of LocalDateTime=" + dateTime);

    }

}