/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_InfoUpdatable;

/**
 * Вопрос. Для БД. Под Hibernate
 *
 * @author VVolgina
 */
@Entity
@Table(name = "Question")
@SuppressWarnings("serial")
public class Question extends Data_InfoUpdatable implements Serializable {
    @ManyToOne
    private TicketTemplate ticketTemplate;

    //<editor-fold defaultstate="collapsed" desc="поля">
    @Column(name = "TEXT", length = 10000)
    private String text = "";
    @Column(name = "FILE", length = 300)
    private String fileName = "";
    private int num;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<Answer>();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    
    public TicketTemplate getTicketTemplate() {
        return ticketTemplate;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="get-set">
    public void setTicketTemplate(TicketTemplate ticketTemplate) {    
        this.ticketTemplate = ticketTemplate;
    }

    /**
     * Возвращает текст вопроса
     *
     * @return текст вопроса
     */
    public String getText() {
        return text;
    }

    /**
     * Назначает текст вопроса
     *
     * @param text текст вопроса
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Возвращает название файла с прикрепленным изображением
     *
     * @return название файла с прикрепленным изображением
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Назначает название файла с прикрепленным изображением
     *
     * @param fileName название файла с прикрепленным изображением
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Возвращает список ответов на вопрос
     *
     * @return список ответов на вопрос
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Назначает список ответов на вопрос
     *
     * @param answers список ответов на вопрос
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    //</editor-fold>
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты
     * создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Question obj) {
        super.cloneForDB(obj);
        answers = obj.answers;
        fileName = obj.fileName;
        text = obj.text;
    }

    @Override
    public String toString() {
        return text ;
    }


}
