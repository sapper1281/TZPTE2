/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import rzd.vivc.astzpte.beans.session.AutorizationBean;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.model.UserModel;
import rzd.vivc.astzpte.repository.TicketRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class TicketsBean  implements Serializable{

    @ManagedProperty(value = "#{autorizationBean}")
    private AutorizationBean session;
    ;
    private List<Ticket> tickets;

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает список доступных незавершенных билетов
     *
     * @return список доступных незавершенных билетов
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Назначает сессионные перемен6ные
     *
     * @param session сессионные перемен6ные
     */
    public void setSession(AutorizationBean session) {
        this.session = session;
    }
    //</editor-fold>

    /**
     * Creates a new instance of TicketsBean
     */
    public TicketsBean() {
    }

    /**
     * Инициализация бина. загрузка списка назавершенных билетов.
     */
    @PostConstruct
    public void init() {
        tickets = (new TicketRepository()).getUnfinishedTickets(session.getCurrentUser().getUserID());
        if (tickets == null) {
            Ticket createNewTicket = (new TicketRepository()).createNewTicket(session.getCurrentUser().getUserID(),0);
            session.getCurrentUser().setTicket(createNewTicket);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("usermain.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(TicketsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String selectTicket(long id) {
        session.getCurrentUser().setTicketID(id);
        return "usermain";
    }

    public String generate() {
        Ticket createNewTicket = (new TicketRepository()).createNewTicket(session.getCurrentUser().getUserID(),0);
        session.getCurrentUser().setTicket(createNewTicket);
        return "usermain";
    }
}
