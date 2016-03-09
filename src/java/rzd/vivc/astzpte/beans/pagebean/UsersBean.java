/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.repository.TicketRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@ViewScoped
public class UsersBean {

    private long toSearch;
    private List<Ticket> tickets=new ArrayList<Ticket>();
    private Ticket currentTicket;

    /**
     * Для поиска пользователей с этим текстом в фамилии
     *
     * @return Для поиска пользователей с этим текстом в фамилии
     */
    public long getToSearch() {
        return toSearch;
    }

    /**
     * Для поиска пользователей с этим текстом в фамилии
     *
     * @param toSearch Для поиска пользователей с этим текстом в фамилии
     */
    public void setToSearch(long toSearch) {
        this.toSearch = toSearch;
    }

    /**
     * Список нвйденных билетов
     *
     * @return Список нвйденных билетов
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Список нвйденных билетов
     *
     * @param tickets Список нвйденных билетов
     */
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     *Выбранный для просмотра билет
     * @return Выбранный для просмотра билет
     */
    public Ticket getCurrentTicket() {
        return currentTicket;
    }

    /**
     * Выбранный для просмотра билет
     * @param currentTicket Выбранный для просмотра билет
     */
    public void setCurrentTicket(Ticket currentTicket) {
        this.currentTicket = currentTicket;
    }
    
    

    /**
     * Creates a new instance of UsersBean
     */
    public UsersBean() {
    }

    /**
     * Поиск билетов у которых в фамилии ползователя еть нужный текст
     */
    public void doSearch() {
        if (toSearch != 0) {
            try {
                tickets = new TicketRepository().findByNum(toSearch);
            } catch (Exception e) {
                tickets = new ArrayList<Ticket>();
            }
        }
    }
    
    /**
     * Переход по ссылке при выборе билета
     * @param id id билета
     */
    public void selectTicket(long id){
        for (Ticket ticket : tickets) {
            if(ticket.getId()==id){
                currentTicket=ticket;
                break;
            }
        }
    }
}
