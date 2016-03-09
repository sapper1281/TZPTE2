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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_InfoUpdatable;

/**
 * Темы. для БД под Хибернейт
 *
 * @author VVolgina
 */
@Entity
@Table(name = "THEME")
@SuppressWarnings("serial")
public class Theme extends Data_InfoUpdatable  implements Serializable{
    @OneToMany(mappedBy = "theme")
    private List<TicketTemplate> ticketTemplates;

    //<editor-fold defaultstate="collapsed" desc="поля">
    @Column(length = 1000, nullable = false)
    private String name = "";
    //</editor-fold>

    public Theme(long id) {
        this.setId(id);
    }

    public Theme() {
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает название темы
     *
     * @return название темы
     */
    public String getName() {
        return name;
    }

    /**
     * Назначает название темы
     *
     * @param name название темы
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает список вопросов,относящихся к данной теме
     * @return список вопросов,относящихся к данной теме
     */
   /* public List<Question> getQuestions() {
        
        return questions;
    }

    public List<Question> getExistingQuestiond(){
        List<Question> res=new ArrayList<Question>();
        for (Question question : questions) {
            if(!question.isDeleted()){
                res.add(question);
            }
        }
        return res;
    }*/
    
    /**
     * Назначает список вопросов,относящихся к данной теме
     * @param questions список вопросов,относящихся к данной теме
     */
 /*   public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }*/
    //</editor-fold>

    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id, списков и даты
     * создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Theme obj) {
        super.cloneForDB(obj);
        name = obj.name;
    }

    @Override
    public String toString() {
        return "Theme{" + "name=" + name + super.toString() + '}';
    }
}
