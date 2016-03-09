/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.model;

import java.io.Serializable;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;

/**
 *
 * @author VVolgina
 */
public class UserView  implements Serializable{

    private User user;
    private int questionCount;
    private UserAnswer currentQuestion=new UserAnswer();
    private int correct;
    private int incorrect;
    private int remain;

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
    
    

    public UserView(User user, int questionCount) {
        this.user = user;
        this.questionCount = 50;
    }

    public UserView(User user, int questionCount, UserAnswer currentQuestion, int correct, int incorrect) {
        this.user = user;
        this.questionCount = 50;
        this.currentQuestion = currentQuestion;
        this.correct = correct;
        this.incorrect = incorrect;
    }
    
    public UserView(User user, int questionCount, UserAnswer currentQuestion, int correct, int incorrect, int remain) {
        this.user = user;
        this.questionCount = 50;
        this.currentQuestion = currentQuestion;
        this.correct = correct;
        this.incorrect = incorrect;
        this.remain=remain;
    }

    public UserView() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public UserAnswer getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(UserAnswer currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }
}
