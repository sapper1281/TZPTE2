/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;

/**
 * Ответ, который пользователь дал на вопрос в процессе прохождение теста
 *
 * @author VVolgina
 */
@Entity
@Table(name = "User_Answer")
@SuppressWarnings("serial")
public class UserAnswer extends Data_Info  implements Serializable{
    
    //<editor-fold defaultstate="collapsed" desc="поля">

    @ManyToOne
    private Question question = new Question();
    @ManyToOne
    private Answer answer = null;
    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket = new Ticket();
    //</editor-fold>

    public UserAnswer() {
    }

    /**
     * Создает экземпляр класса
     *
     * @param q вопрос, на который отвечал пользователь
     * @param t билеит, в котором вопрос
     */
    public UserAnswer(Question q, Ticket t) {
        question = q;
        ticket = t;
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает вопрос, на который отвечал пользователь
     *
     * @return вопрос, на который отвечал пользователь
     */
   public Question getQuestion() {
        return question;
    }

    /**
     * Возвращает ответ пользователя
     *
     * @return ответ пользователя
     */
    public Answer getAnswer() {
        return answer;
    }

    /**
     * Назначает ответ пользователя
     *
     * @param answer ответ пользователя
     * @throws IllegalArgumentException Не соответствуют друг другу id вопроса и ответа
     */
    public void setAnswer(Answer answer) throws IllegalArgumentException {
        if (isAnswerAppropriate(answer)) {
            this.answer = answer;
        } else {
            throw new IllegalArgumentException("Нужно дать ответ на тот вопрос, который был задан");
        }
    }

    /**
     * Возвращает билет, в котором был дан ответ на вопрос
     *
     * @return билет, в котором был дан ответ на вопрос
     */
    public Ticket getTicket() {
        return ticket;
    }

    //</editor-fold>
    
    @Override
    public String toString() {
        return "UserAnswer{" + "question=" + question + ", answer=" + answer + ", " + super.toString() + '}';
    }

    private boolean isAnswerAppropriate(Answer a) {
        if (a.getQuestion().getId() != question.getId()) {
            return false;
        }
        for (Answer object : question.getAnswers()) {
            if (object.getId() == a.getId()) {
                return true;
            }
        }
        return false;
    }
}
