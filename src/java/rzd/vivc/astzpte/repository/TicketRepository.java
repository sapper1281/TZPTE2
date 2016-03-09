/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.TicketTemplate;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.dto.tickets.TicketGenerator;
import zdislava.common.dto.configuration.SessionFactorySingleton;
import zdislava.common.vocabulary.PartName;

/**
 *
 * @author VVolgina
 */
public class TicketRepository {

    protected void save(Ticket elem, Session sess) {
        sess.saveOrUpdate(elem);
    }

    /**
     * Возвращает список незавершенных билетов для данного пользователя
     *
     * @param userID Id пользователя
     * @return список незавершенных билетов для данного пользователя
     */
    public List<Ticket> getUnfinishedTickets(long userID) {
        List<Ticket> res = new ArrayList<Ticket>();
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        List<Ticket> tickets = (ArrayList<Ticket>) sess.createQuery("from Ticket where user=:usr").setLong("usr", userID).list();
        t.commit();
        sess.close();

        for (Ticket ticket : tickets) {
            if (!isTicketFinished(ticket)) {
                res.add(ticket);
            }
        }
        if (res.isEmpty()) {
            res = null;
        }

        return res;
    }

    /* public List<Ticket> getUnfinishedTickets(long userID) {
     Session sess = SessionFactorySingleton.getSessionFactoryInstance()
     .openSession();
     Transaction t = sess.beginTransaction();
     List<Ticket> tickets = (ArrayList<Ticket>) sess.createQuery("from Ticket where user=:usr").setLong("usr", userID).list();
     t.commit();
     sess.close();

     for (Ticket ticket : tickets) {
     if (isTicketFinished(ticket)) {
     tickets.remove(ticket);
     }
     }

     return tickets;
     }*/
    private boolean isTicketFinished(Ticket tick) {
        List<UserAnswer> answers = tick.getAnswers();
        if (answers == null || answers.isEmpty()) {
            return false;
        }
        for (UserAnswer userAnswer : answers) {
            if (userAnswer.getAnswer() == null) {
                return false;
            }
        }
        return true;
    }

    public Ticket createNewTicket(long userID, long themeID) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным ФИО
        User bd = (User) sess.get(User.class, userID);
        TicketGenerator gen;
         TicketTemplate ticketTemplate=new TicketTemplateRepository().getWithQuestions(themeID);
         gen = new TicketGenerator(bd);
         gen.generateNewTicket(ticketTemplate);
         (new TicketRepository()).save(bd.getTickets().get(bd.getTickets().size()-1), sess);
        t.commit();
        sess.close();
        return bd.getTickets().get(bd.getTickets().size() - 1);
    }

    public void updateTime(int remain, long tickID) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        sess.createQuery("UPDATE Ticket as t SET t.time=:rem WHERE t.id=:id")
                .setInteger("rem", remain)
                .setLong("id", tickID)
                .executeUpdate();

        t.commit();
        sess.close();

    }
    
        public void updateTime( long tickID) {
            Date now=new Date();
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        
        Ticket tick=(Ticket)sess.createQuery("select t from Ticket as t WHERE t.id=:id")
                .setLong("id", tickID)
                .uniqueResult();
        tick.setFinish(now);
        sess.saveOrUpdate(tick);
        t.commit();
        sess.close();
 
    }

    public Ticket get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        Ticket get = (Ticket) sess.get(Ticket.class, id);
        t.commit();
        sess.close();

        return get;
    }

    public List<Ticket> findByFIO(String toSearch) {
        List<Ticket> bd = null;
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        bd = sess.createQuery("SELECT T FROM Ticket AS T LEFT JOIN T.user as u WHERE u.surname||' '||u.name||' '||u.patronomicname LIKE :val").setString("val", "%" + toSearch + "%").list();

        t.commit();
        sess.close();
        return bd;
    }
    
    public List<Ticket> findByNum(long toSearch) {
        List<Ticket> bd = null;
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        bd = sess.createQuery("SELECT T FROM Ticket AS T LEFT JOIN T.user as u WHERE u.num=:val").setLong("val", toSearch).list();

        t.commit();
        sess.close();
        return bd;
    }

    public static void main(String[] args) {

    }
}
