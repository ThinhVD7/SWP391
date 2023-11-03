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