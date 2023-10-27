package Model;


public class StudentResult {
    private String resultID;
    private String examID;
    private String studentID;
    private String studentAnswer;
    private String rightsAndWrongs;
    private String flags;
    private float totalScore;
    private String totalTime;
    private int state;

    public StudentResult() {
    }

    public StudentResult(String resultID, String examID, String studentID, String studentAnswer, String rightsAndWrongs, String flags, float totalScore, String totalTime, int state) {
        this.resultID = resultID;
        this.examID = examID;
        this.studentID = studentID;
        this.studentAnswer = studentAnswer;
        this.rightsAndWrongs = rightsAndWrongs;
        this.flags = flags;
        this.totalScore = totalScore;
        this.totalTime = totalTime;
        this.state = state;
    }

    public String getResultID() {
        return resultID;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getRightsAndWrongs() {
        return rightsAndWrongs;
    }

    public void setRightsAndWrongs(String rightsAndWrongs) {
        this.rightsAndWrongs = rightsAndWrongs;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
    
}
