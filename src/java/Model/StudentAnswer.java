
package Model;

public class StudentAnswer {
    private String StudentAnswer_ID;
    private int Question_ID;
    private int Choice_ID;
    private int Result_ID;
    
    public StudentAnswer(){}
    
    public StudentAnswer(String StudentAnswer_ID, int Question_ID, int Choice_ID, int Result_ID){
        StudentAnswer_ID = this.StudentAnswer_ID;
        Question_ID = this.Question_ID;
        Choice_ID = this.Choice_ID;
        Result_ID = this.Result_ID;
    }

    public String getStudentAnswer_ID() {
        return StudentAnswer_ID;
    }

    public void setStudentAnswer_ID(String StudentAnswer_ID) {
        this.StudentAnswer_ID = StudentAnswer_ID;
    }

    public int getQuestion_ID() {
        return Question_ID;
    }

    public void setQuestion_ID(int Question_ID) {
        this.Question_ID = Question_ID;
    }

    public int getChoice_ID() {
        return Choice_ID;
    }

    public void setChoice_ID(int Choice_ID) {
        this.Choice_ID = Choice_ID;
    }

    public int getResult_ID() {
        return Result_ID;
    }

    public void setResult_ID(int Result_ID) {
        this.Result_ID = Result_ID;
    }
    
}
