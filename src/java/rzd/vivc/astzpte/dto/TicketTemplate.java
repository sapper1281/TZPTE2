/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;

/**
 *
 * @author VVolgina
 */
@Entity
@Table(name = "ticket_template")
public class TicketTemplate extends Data_Info implements Serializable {


    @ManyToOne
    private Theme theme = new Theme();
    private long num;
    @OneToMany(mappedBy = "ticketTemplate")
    private List<Question> questions=new ArrayList<>();

    public TicketTemplate(long i) {
        setId(i);
    }

    public TicketTemplate() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

}
