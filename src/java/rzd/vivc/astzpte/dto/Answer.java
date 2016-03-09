/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import rzd.vivc.astzpte.dto.baseclass.Data_InfoUpdatable;

/**
 * Ответ на вопрос. Для БД. Под Hibernate
 * @author VVolgina
 */
@Entity
@Table(name = "Answer")
@SuppressWarnings("serial")
public class Answer extends Data_InfoUpdatable implements Serializable{
    
    //<editor-fold defaultstate="collapsed" desc="Поля">
    @Column(name = "TEXT", length = 10000)
    private String text="";
    @Column(name="CORRECT_ANSWER")
    private boolean correct=false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question =new Question();
    
    @Transient
    private String correct_name;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="get-set">
    public String getCorrect_name() {
        if (correct) {  return "Правильный";} 
        else return "";
       
    }

    public void setCorrect_name(String correct_name) {
        this.correct_name = correct_name;
    }
    
    
    
    
    
    /**
     * Возвращает текст ответа
     * @return текст ответа
     */
    public String getText() {
        return text;
    }
    
    /**
     * Назначает текст ответа
     * @param text текст ответа
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Показывает, верный ли ответ
     * @return - True, Если ответ верный
     * false - если неверный
     */
    public boolean isCorrect() {
        return correct;
    }
    
    /**
     * Назначает корректность ответа
     * @param correct верный ли ответ
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    /**
     * Возвращает вопрос, к которому относится данный ответ
     * @return вопрос, к которому относится данный ответ
     */
    public Question getQuestion() {
        return question;
    }
    
    /**
     * Назначает вопрос, к которому относится данный ответ
     * @param question вопрос, к которому относится данный ответ
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
    
    
   
    
    //</editor-fold>
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id, списков и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Answer obj) {
        super.cloneForDB(obj);
        text=obj.text;
        correct=obj.correct;
        question=obj.question;
    }
    
    @Override
    public String toString() {
        return "Answer{" + "text=" + text + ", correct=" + correct  + ", "+super.toString()+'}';
    }
}
