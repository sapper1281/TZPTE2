/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;
import zdislava.common.security.users.Person;

/**
 * Билеты. В билет входят не только вопросы-ответы, но и ФИО того, кто проходил
 * тест
 *
 * @author VVolgina
 */
@Entity
@Table(name = "ticket")
@SuppressWarnings("serial")
public class Ticket extends Data_Info  implements Serializable{
    //<editor-fold defaultstate="collapsed" desc="поля">

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<UserAnswer> answers = new ArrayList<UserAnswer>();
    @Column(name = "USED_TIME")
    private int time = 0;
    @ManyToOne
    @JoinColumn(name = "User")
    private User user;
    @Column(name = "IP_BEGIN")
    private String ipBegin;
    @Column(name = "IP_END")
    private String ipEnd;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date finish;
    //</editor-fold>

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    public Ticket() {
        this.user = new User();
    }

    /**
     * Возвращает список ответов пользователя
     *
     * @return список ответов пользователя
     */
    public List<UserAnswer> getAnswers() {
        return answers;
    }

    /**
     * Назначает список ответов пользователя
     *
     * @param answers список ответов пользователя
     */
    public void setAnswers(List<UserAnswer> answers) {
        this.answers = answers;
    } 

    /**
     * Назначает список вопросов для пользователя
     *
     * @param questions список вопросов для пользователя
     */
    public void setQuestions(List<Question> questions) {
        for (Question question : questions) {
            this.answers.add(new UserAnswer(question, this));
        }
    }

    /**
     * Возвращает время, затраченное на прохождение
     *
     * @return время, затраченное на прохождение
     */
    public int getTime() {
        return time;
    }

    /**
     * Назначает время, затраченное на прохождение
     *
     * @param time время, затраченное на прохождение
     */
    public void setTime(int time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIpBegin() {
        return ipBegin;
    }

    public void setIpBegin(String ipBegin) {
        this.ipBegin = ipBegin;
    }

    public String getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(String ipEnd) {
        this.ipEnd = ipEnd;
    }
    
    public String getDateString(){
        return new SimpleDateFormat("hh:mm dd.MM.yyyy").format(getDt_create());
    }

    //</editor-fold>
    @Override
    public String toString() {
        return "Ticket{" + '}';
    }
}
