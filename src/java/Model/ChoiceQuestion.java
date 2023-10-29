/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tanki
 */
public class ChoiceQuestion {

    private int choiceId;
    private String questionId;
    private String choiceContent;
    private int choicePercentage;

    public ChoiceQuestion() {
    }

    public ChoiceQuestion(int choiceId, String questionId, String choiceContent, int choicePercentage) {
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.choiceContent = choiceContent;
        this.choicePercentage = choicePercentage;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getChoiceContent() {
        return choiceContent;
    }

    public void setChoiceContent(String choiceContent) {
        this.choiceContent = choiceContent;
    }

    public int getChoicePercentage() {
        return choicePercentage;
    }

    public void setChoicePercentage(int choicePercentage) {
        this.choicePercentage = choicePercentage;
    }

}
