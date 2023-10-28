package Model;


public class Exam {
    private String examID;
    private String classID;
    private String examName;
    private int questionNumber;
    private String startDate;
    private String endDate;
    private String timeLimit;
    private int attempsAllowed;
    private String examDetail;
    private float maxScore;
    private int permission;

    public Exam() {
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

    public void setAttempsAllowed(int attempsAllowed) {
        this.attempsAllowed = attempsAllowed;
    }

    public String getExamDetail() {
        return examDetail;
    }

    public void setExamDetail(String examDetail) {
        this.examDetail = examDetail;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
    
    
}
