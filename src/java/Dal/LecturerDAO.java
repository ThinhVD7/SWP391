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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public List<Question> questionList = new ArrayList<>();
    public List<Question> questionList2 = new ArrayList<>();

    public List<ChoiceQuestion> choiceList = new ArrayList<>();
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

////student///////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String, Course> loadAllStudentCourse(String studentID) {
        String sql = "select course.Course_ID, course.CourseName, course.Semester, course.StartDate, course.EndDate  from course join class on course.Course_ID = class.Course_ID join studentinwhichclass on studentinwhichclass.Class_ID = class.Class_ID where studentinwhichclass.Student_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, studentID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseHashMap.put(rs.getString(1),
                        new Course(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)));
            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return courseHashMap;
    }

////student///////////////////////////////////////////////////////////////////////////////////////////////////////    
////load single record directly////////////////////////////////////////////////////////////////////////////////////
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
            status = "Error at load a course" + e.getMessage();
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
            status = "Error at load a class" + e.getMessage();
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
                        rs.getString(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12));
            }
        } catch (Exception e) {
            status = "Error at load a exam" + e.getMessage();
        }
        return null;
    }

    public Account loadALecturerofClass(String classID) {
        String sql = "SELECT account.Account_ID, account.Name, account.Email FROM account join lecturerinwhichclass on account.Account_ID = lecturerinwhichclass.Lecturer_ID where Class_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        "");
            }
        } catch (Exception e) {
            status = "Error at load lecturer of a class" + e.getMessage();
        }
        return null;
    }

////load all where////////////////////////////////////////////////////////////////////////////////////    
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
            status = "Error at load studentlist of a class" + e.getMessage();
        }
        return accountList;
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
                        rs.getString(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            status = "Error at load exam list" + e.getMessage();
        }
        return examList;
    }

////minor field////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public String getStringFormattedDate(String timeLimit_date_dateTime, String input) {
        String output = "";
        LocalTime time;
        if (timeLimit_date_dateTime.equalsIgnoreCase("timeLimit")) {
            time = LocalTime.parse(input);
            output += (time.getHour() == 0 ? "" : (time.getHour() + " hour" + (time.getHour() == 1 ? "" : "s")));
            output += " " + (time.getMinute() == 0 ? "" : (time.getMinute() + " minute" + (time.getMinute() == 1 ? "" : "s")));
            output += " " + (time.getSecond() == 0 ? "" : (time.getSecond() + " second" + (time.getSecond() == 1 ? "" : "s")));
        } else if (timeLimit_date_dateTime.equalsIgnoreCase("date")) {
            output = LocalDate.parse(input).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else if (timeLimit_date_dateTime.equalsIgnoreCase("dateTime")) {
            output = LocalDateTime.parse(input).format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"));
        }
        return output;
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

    ////exam handle///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean addExam(String classId, String examName, String questionNumber, String timeLimit, String startDate, String endDate, String examDetail, String examScore, int permission, String createdBy) {
        try {
            String sql = "INSERT INTO exam\n"
                    + "(Class_ID,\n"
                    + "ExamName,\n"
                    + "QuestionNumber,\n"
                    + "StartDate,\n"
                    + "EndDate,\n"
                    + "TimeLimit,\n"
                    + "ExamDetail,\n"
                    + "MaxScore,\n"
                    + "Permission,\n"
                    + "CreatedBy)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classId);
            ps.setString(2, examName);
            ps.setString(3, questionNumber);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.setString(6, timeLimit);
            ps.setString(7, examDetail);
            ps.setString(8, examScore);
            ps.setInt(9, permission);
            ps.setString(10, createdBy);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    //update exam
    public boolean updateExam(String examId, String classId, String examName, String timeLimit, String startDate, String endDate, String examDetail, int permission) {
        try {
            String sql = "update exam set Class_ID = ?,ExamName = ?,StartDate = ?,EndDate = ?,TimeLimit = ?,ExamDetail = ?,Permission = ? where Exam_ID = ?";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, classId);
            ps.setString(2, examName);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            ps.setString(5, timeLimit);
            ps.setString(6, examDetail);
            ps.setInt(7, permission);
            ps.setString(8, examId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateExamScore(String examId, String maxScore, String QuestionNumber) {
        try {
            String sql = "update exam set MaxScore = ?,QuestionNumber = ? where Exam_ID = ?";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, maxScore);
            ps.setString(2, QuestionNumber);
            ps.setString(3, examId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addQuestion(String Title, String questionContent, String type, float mark) {
        try {
            String sql = "INSERT INTO question\n"
                    + "(Title,\n"
                    + "QuestionContent,\n"
                    + "Type,\n"
                    + "Mark)\n"
                    + "VALUES(?,?,?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, questionContent);
            ps.setString(3, type);
            ps.setFloat(4, mark);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public Question getLastestQuestion() {
        String sql = "SELECT * FROM question ORDER BY Question_ID DESC limit 1;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
            System.out.println(status);
        }
        return null;
    }

    public boolean doesQuestionExistInBank(String qId, String bId) {
        String sql = "SELECT * FROM questioninwhichbank WHERE Bank_ID = ? AND Question_ID = ?;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, bId);
            ps.setString(2, qId);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // Return true if there is a matching record, false otherwise
        } catch (Exception e) {
            status = "Error at checking if question exists in bank: " + e.getMessage();
            System.out.println(status);
            return false; // Return false in case of an error
        }
    }

    public Bank getBankByCourseId(String courseId, String lecturerId) {
        String sql = "SELECT * FROM `quiz9.8`.bank where Course_ID = ? AND Lecturer_ID = ?;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, courseId);
            ps.setString(2, lecturerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Bank(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
            System.out.println(status);
        }
        return null;
    }

    public boolean addToBank(String bankId, String Question_ID) {
        try {
            String sql = "INSERT INTO questioninwhichbank\n"
                    + "(Bank_ID,\n"
                    + "Question_ID)\n"
                    + "VALUES(?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, bankId);
            ps.setString(2, Question_ID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public void addNewBank(String CourseID, String LecturerID) {
        try {
            String sql = "INSERT INTO Bank\n"
                    + "(Course_ID,\n"
                    + "Lecturer_ID)\n"
                    + "VALUES(?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, CourseID);
            ps.setString(2, LecturerID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public List<Question> getListQuestionByExamID(String examId) {
        String sql = "SELECT b.* FROM questioninwhichexam a join question b on a.Question_ID = b.Question_ID where Exam_ID = ?";

        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, examId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questionList2.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5)));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
            System.out.println(status);
        }
        return questionList2;
    }

    public List<Question> getListQuestionByBank(String bankId) {
        String sql = "SELECT b.* FROM questioninwhichbank a join question b on a.Question_ID = b.Question_ID where Bank_ID = ?";

        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, bankId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questionList.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5)));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
        }
        return questionList;
    }

    public Exam getLastExam() {
        String sql = "SELECT * FROM exam ORDER BY Exam_ID DESC limit 1;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10), rs.getString(11), rs.getInt(12));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }

    public Exam getExam(String examID) {
        String sql = "SELECT * FROM exam where Exam_ID = ?;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, examID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10), rs.getString(11), rs.getInt(12));

            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }

    public boolean addChoice(String Question_ID, String ChoiceContent, String ScorePercentage) {
        try {
            String sql = "INSERT INTO choicesofquestion\n"
                    + "(Question_ID,\n"
                    + "ChoiceContent,\n"
                    + "ScorePercentage)\n"
                    + "VALUES(?,?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, Question_ID);
            ps.setString(2, ChoiceContent);
            ps.setString(3, ScorePercentage);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean addBank(String Exam_ID, String Question_ID) {
        try {
            String sql = "INSERT INTO questioninwhichexam\n"
                    + "(Exam_ID,\n"
                    + "Question_ID)\n"
                    + "VALUES(?,?)";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, Exam_ID);
            ps.setString(2, Question_ID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public void deleteExamByID(String examID) {
        String sql = "";
        try {
            sql = "delete from exam where Exam_ID = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps = connector.prepareStatement(sql);
            ps.setString(1, examID);
            ps.executeUpdate();
            sql = "delete from questioninwhichexam where Exam_ID = ?";
            ps = connector.prepareStatement(sql);
            ps.setString(1, examID);
            ps.executeUpdate();
            sql = "delete from studentresult where Exam_ID = ?";
            ps = connector.prepareStatement(sql);
            ps.setString(1, examID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteChoiceQuestion(String qId) {
        String sql = "";
        try {
            sql = "delete from choicesofquestion where Choice_ID = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps = connector.prepareStatement(sql);
            ps.setString(1, qId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteQuestionExam(String questionId, String examID) {
        String sql = "";
        try {
            sql = "delete from questioninwhichexam where Question_ID = ? and Exam_ID = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps = connector.prepareStatement(sql);
            ps.setString(1, questionId);
            ps.setString(2, examID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Question getAQuestion(String questionID) {
        String sql = "";

        try {
            sql = "select * from question where Question_ID = ?";
            PreparedStatement ps = connector.prepareStatement(sql);
            ps = connector.prepareStatement(sql);
            ps.setString(1, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Class1> getClassByCourseId(String courseId) {
        String sql = "SELECT * FROM class where Course_ID like ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, courseId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classList.add(new Class1(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));

            }
        } catch (Exception e) {
            status = "Error at load courses" + e.getMessage();
        }
        return classList;
    }

    public boolean updateQuestion(String questionId, String Title, String questionContent, String type, float mark) {
        try {
            String sql = "update question set Title = ?,QuestionContent = ?,Type = ?,Mark = ? where Question_ID = ?";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, questionContent);
            ps.setString(3, type);
            ps.setFloat(4, mark);
            ps.setString(5, questionId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    //have not modified to database
    public Question getQuestionById(String qId) {
        String sql = "SELECT * FROM question where Question_ID = ?";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, qId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString(4);
                return new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
            }
        } catch (Exception e) {
            status = "Error at get Account " + e.getMessage();
        }
        return null;
    }

    public List<ChoiceQuestion> getChoiceOfQuestion(String questionId) {
        String sql = "SELECT * from choicesofquestion where Question_ID = ?";

        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, questionId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                choiceList.add(new ChoiceQuestion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
            status = "Error at load classes" + e.getMessage();
        }
        return choiceList;
    }

    public boolean updateChoice(String Question_ID, String ChoiceContent, String ScorePercentage, String choice_ID) {
        try {
            String sql = "update choicesofquestion set ChoiceContent = ?,ScorePercentage = ? where Question_ID = ? and Choice_ID = ?";

            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, ChoiceContent);
            ps.setString(2, ScorePercentage);
            ps.setString(3, Question_ID);
            ps.setString(4, choice_ID);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }
    
    public boolean doesQuestionExistInBankByTitleAndContent(String title, String content) {
        String sql = "SELECT * FROM question WHERE Title = ? AND QuestionContent = ?;";
        try {
            PreparedStatement ps = connector.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // Return true if there is a matching record, false otherwise
        } catch (Exception e) {
            status = "Error at checking if question exists in bank: " + e.getMessage();
            System.out.println(status);
            return false; // Return false in case of an error
        }
    }

    public static void main(String[] args) {
        LecturerDAO dao = new LecturerDAO();
        System.out.println(dao.getListQuestionByBank("3").get(1).getQuestionID());

//        System.out.println(test.getExamID());
//        //test user
//        Account acc = dao.getUser("nampthe171400@fpt.edu.vn");
//        System.out.println(acc.getEmail());
//        
//        //test load a course
//        System.out.println(dao.loadACourse("JPD134").getCourseID()+" "+dao.loadACourse("JPD134").getSemester());
//        
//        //test load all course
//        dao.loadAllCourses("minhvnt_he_176043");
//        
//        //test load all class of a course
//        List<Class1> classList = dao.loadAllClassesofCourse("minhvnt_he_176043", "MAS291");
//        for (int i = 0; i < classList.size(); i++) 
//        {
//            System.out.println(classList.get(i).getClassName()+classList.get(i).getClassID());
//        }
//        
//       //test load all courses of lecturer
//        List<Course> list = dao.loadAllCourses("minhvnt_he_176043");
//        if(list ==null)
//            System.out.println("null");
//        for (int i = 0; i < list.size(); i++) 
//        {
//            System.out.println(list.get(i).getCourseID());
//        }
//        
//        //test load all exam of a class
//        List<Exam> examList = dao.loadAllExamofClass("SE1732_MAS291");
//        for (int i = 0; i < examList.size(); i++) 
//        {
//            System.out.println(examList.get(i).getExamName()+examList.get(i).getClassID());
//        }
//        
//        //test number of student
//        System.out.println(dao.numberofStudentofClass("SE1732_MAS291"));
//        
//        //test number of exam
//        System.out.println(dao.numberofExamofClass("SE1732_MAS291"));
//        
//        HashMap<String, Integer> class_studentNumber = new HashMap<String,Integer>();
//        HashMap<String, Integer> class_examNumber = new HashMap<String,Integer>();
//        for (Class1 class1 : classList) 
//        {
//            class_studentNumber.put(class1.getClassID(), dao.numberofStudentofClass(class1.getClassID()));
//            class_examNumber.put(class1.getClassID(), dao.numberofExamofClass(class1.getClassID()));
//        }
//        
//        System.out.println(class_studentNumber);
//        System.out.println(class_examNumber);
        //test getDateFormattedString
//        System.out.println(dao.getDateforDisplay("date", ));
        //parse date
        /*
        String parsetest = "12-23-2023";
        LocalDate dateTest1 = LocalDate.parse(parsetest, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println("dateTest 1:" + dateTest1);
        LocalDate dateTest = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("dateTest: " + dateTest);

        //parse dateTime
        String dateTimeFormatTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println("dateTimeFormatTest" + dateTimeFormatTest);
//        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeFormatTest);
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeParseTest = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("dateTimeParseTest: " + dateTimeParseTest);
        System.out.println("dateTime string format: " + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm")));
        //default format
        System.out.println("Default format of LocalDateTime=" + dateTime);
        System.out.println("this: " + LocalDateTime.parse("2023-12-13T07:09:12"));
        byte y = 9;
        System.out.println(y);

        // create a LocalTime Objects 
        LocalTime time1 = LocalTime.parse("00:45:00.100");
        LocalTime time2 = LocalTime.parse("00:45:00");
        LocalTime time3 = LocalTime.NOON;
        System.out.println("time3 " + time3);

        System.out.println("time1 compare to time2: " + ((time1.compareTo(time2) == 1) ? "Bigger" : (time1.compareTo(time2))));
        // create formatter Object 
        DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;

        // apply format 
        String value = time1.format(formatter);

        // print result 
        System.out.println("value : " + value);

        //student
        System.out.println(dao.loadAllStudentCourse("dungnt_he_176358").get("MAS291").getCourseName());

        LocalDateTime testExamDate = LocalDateTime.parse(dao.getExam("20").getStartDate());
        System.out.println(testExamDate);
        System.out.println(LocalDateTime.now().compareTo(testExamDate));
        LocalDateTime today = LocalDateTime.now();
        Exam exam = dao.getExam("21");

        if (today.compareTo(LocalDateTime.parse(exam.getStartDate())) > -1 && today.compareTo(LocalDateTime.parse(exam.getEndDate())) < 0) {
            System.out.println(today.compareTo(LocalDateTime.parse(exam.getStartDate())));
            System.out.println(today.compareTo(LocalDateTime.parse(exam.getEndDate())));
            System.out.println();
        }*/
    }

}
