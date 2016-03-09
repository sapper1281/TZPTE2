/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.model;

import java.io.Serializable;
import java.util.List;
import rzd.vivc.astzpte.dto.Answer;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.UserAnswer;

/**
 *
 * @author VVolgina
 */
public class UserAnswerModel  implements Serializable{
    private UserAnswer userAnswer;
    private int questionNumber;

    public Question getQuestion() {
        return userAnswer.getQuestion();
    }
    
    public Answer getAnswer(){
        return userAnswer.getAnswer();
    }

    public int correctNumber(){
        List<Answer> answers = userAnswer.getQuestion().getAnswers();
        for (int i=0; i<answers.size(); i++) {
            if(answers.get(i).isCorrect()){
                return i+1;
            }
        }
        return -1;
    }
    
    public int givenNumber(){
        List<Answer> answers = userAnswer.getQuestion().getAnswers();
        for (int i=0; i<answers.size(); i++) {
            if(answers.get(i).getId()==userAnswer.getAnswer().getId()){
                return i+1;
            }
        }
        return -1;
    }
    
    public void setUserAnswer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public UserAnswerModel(UserAnswer userAnswer, int questionNumber) {
        this.userAnswer = userAnswer;
        this.questionNumber = questionNumber;
    }
    
    
}
