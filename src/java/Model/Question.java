package Model;


public class Question {
    private String questionID;
    private String title;
    private String content;
    private String type;
    private String choices;
    private String choicePercentages;
    private int answer;
    private int shuffleChoices;
    private float mark;
    private boolean userCheck = false;
    private String examID;
    private String StudentID;

    public Question() {
    }

    public Question(String questionID, String title, String content, String type, String choices, String choicePercentages, int answer, int shuffleChoices, float mark, boolean userCheck, String examID, String StudentID) {
        this.questionID = questionID;
        this.title = title;
        this.content = content;
        this.type = type;
        this.choices = choices;
        this.choicePercentages = choicePercentages;
        this.answer = answer;
        this.shuffleChoices = shuffleChoices;
        this.mark = mark;
        this.userCheck = userCheck;
        this.StudentID = StudentID;
        this.examID = examID;
    }
    
    public Question(String questionID, String title, String content, String type, int shuffleChoices, float mark) {
        this.questionID = questionID;
        this.title = title;
        this.content = content;
        this.type = type;
        this.shuffleChoices = shuffleChoices;
        this.mark = mark;
    }
    public Question(String questionID, String title, String content, String type, float mark) {
        this.questionID = questionID;
        this.title = title;
        this.content = content;
        this.type = type;
        this.mark = mark;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getChoicePercentages() {
        return choicePercentages;
    }

    public void setChoicePercentages(String choicePercentages) {
        this.choicePercentages = choicePercentages;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getShuffleChoices() {
        return shuffleChoices;
    }

    public void setShuffleChoices(int shuffleChoices) {
        this.shuffleChoices = shuffleChoices;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public boolean isUserCheck() {
        return userCheck;
    }

    public void setUserCheck(boolean userCheck) {
        this.userCheck = userCheck;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }
    
}
