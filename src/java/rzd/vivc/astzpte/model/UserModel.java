/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;

/**
 *
 * @author VVolgina
 */
public class UserModel implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="поля">
    
    private long userID = 0;

   private long ticketID=0;
   
   private Date beginDate;

   

    //</editor-fold>
    public UserModel(User user, Ticket tick) {
        userID=user.getId();
        ticketID=tick.getId();
        beginDate=tick.getDt_create();
    }
    
    public UserModel(User user) {
        userID=user.getId();
    }

    public UserModel() {
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    
     /**
     * Get the value of userID
     *
     * @return the value of userID
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Set the value of userID
     *
     * @param userID new value of userID
     */
    public void setUserID(long userID) {
        this.userID = userID;
    }
    
     public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }
    
     public void setUser(User user) {
       setUserID(user.getId());
    }
     
     public void setTicket(Ticket tick) {
       setTicketID(tick.getId());
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
     
    public String getDateString(){
        return beginDate==null?"":(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")).format(beginDate);
    }
     
    //</editor-fold>

   public void reset(){
       ticketID=0;
       userID=0;
       beginDate=null;
   }
   
   
}
