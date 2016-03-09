/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import zdislava.common.security.users.Person;

/**
 *
 * @author VVolgina
 */
@Entity
@Table(name = "User")
@SuppressWarnings("serial")
public class User extends Person implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="поля">
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date numDat = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date allowDat = null;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private long num;
     private String numNum = "";
      private String allowNum = "";

    //</editor-fold>
    public User() {
    }

    public User(String name, String surname, String patronomicname, long DepartmentID) {
        super(name, surname, patronomicname);
    }

    public User(long num) {
        this.num=num;
    }

    public User(String name, String surname, String patronomicname, Department department) {
        super(name, surname, patronomicname);
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    /**
     * дата рождения пользователя
     *
     * @return дата рождения пользователя
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * дата рождения пользователя
     *
     * @param birthday дата рождения пользователя
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    /**
     * Возвращает список билетов этого пользователя
     *
     * @return список билетов этого пользователя
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Добавляет в список билетов еще один
     *
     * @param ticket билет для добавления
     */
    public void addTicket(Ticket ticket) {
        ticket.setUser(this);
        tickets.add(ticket);
    }

    /**
     * Назначает список билетов этого пользователя
     *
     * @param tickets список билетоа этого пользователя
     */
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

//</editor-fold>

    public String getNumNum() {
        return numNum;
    }

    public void setNumNum(String numNum) {
        this.numNum = numNum;
    }

    public String getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(String allowNum) {
        this.allowNum = allowNum;
    }

    
    
    public Date getNumDat() {
        return numDat;
    }

    public void setNumDat(Date numDat) {
        this.numDat = numDat;
    }

    public Date getAllowDat() {
        return allowDat;
    }

    public void setAllowDat(Date allowDat) {
        this.allowDat = allowDat;
    }
}
