
package Model;

public class StudentAnswer {
    private int QuestionID;
    private String ChoiceID;
    private int ExamID;
    private String StudentID;
    
    public StudentAnswer(){}
    public StudentAnswer(int QuestionID, String ChoiceID, int ExamID, String StudentID){
        this.QuestionID = QuestionID;
        this.ChoiceID = ChoiceID;
        this.ExamID = ExamID;
        this.StudentID = StudentID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getChoiceID() {
        return ChoiceID;
    }

    public void setChoiceID(String ChoiceID) {
        this.ChoiceID = ChoiceID;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int ExamID) {
        this.ExamID = ExamID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }
    
}