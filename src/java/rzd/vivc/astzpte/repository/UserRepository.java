package rzd.vivc.astzpte.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.dto.tickets.TicketGenerator;
import rzd.vivc.astzpte.model.UserView;
import zdislava.common.dto.configuration.SessionFactorySingleton;

/**
 * Нужен для сохранения - извлечения из БД объектов, связанных с пользователями
 *
 *
 * @author VVolgina
 *
 */
public class UserRepository {

    /**
     * Пытается найти в БД пользователя с соответствующими данными. Если такого
     * не существует - создает его и генерирует ему билет. Если у пользователя
     * есть старые Незавершенные билеты они тоже загружаются
     *
     * @param surname фамилия
     * @param name имя
     * @param patronomicname отчество
     * @param departmentID id службы
     * @return пользователь из БД
     */
    public User getUser(String surname, String name, String patronomicname, long departmentID, Date birthday) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным ФИО
        User bd = get(surname, name, patronomicname, departmentID, sess);
        TicketGenerator gen;
        if (bd == null) {
          //  Department dep = (new DepartmentRepository()).get(departmentID, sess);
            bd = new User(name, surname, patronomicname, (new DepartmentRepository()).get(departmentID, sess));
            bd.setBirthday(birthday);
           /* gen = new TicketGenerator(bd);
            PartName get = (new PartNameRepository()).get(TZPTEConstants.COUNT_OF_QUESTIONS, sess);
            gen.generateNewTicket(dep.getQuestions(), new Integer(get.getVocabularyValues().get(0).getText()));
            (new TicketRepository()).save(bd.getTickets().get(0), sess);*/
            save(bd, sess);
           /* bd.addTicket(new Ticket());*/
        }
        t.commit();
        sess.close();
        return bd;
    }
    
     /**
     * Пытается найти в БД пользователя с соответствующими данными. Если такого
     * не существует - создает его и генерирует ему билет. Если у пользователя
     * есть старые Незавершенные билеты они тоже загружаются
     *

     * @return пользователь из БД
     */
    public User getUser(long num, String allowNum, String numNum, Date alowDat, Date numDat) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным ФИО
        User bd = get(num,"", sess);
        TicketGenerator gen;
        if (bd == null) {
          //  Department dep = (new DepartmentRepository()).get(departmentID, sess);
            bd = new User(num);
            bd.setAllowDat(alowDat);
            bd.setAllowNum(allowNum);
            bd.setNumDat(numDat);
            bd.setNumNum(numNum);
           /* gen = new TicketGenerator(bd);
            PartName get = (new PartNameRepository()).get(TZPTEConstants.COUNT_OF_QUESTIONS, sess);
            gen.generateNewTicket(dep.getQuestions(), new Integer(get.getVocabularyValues().get(0).getText()));
            (new TicketRepository()).save(bd.getTickets().get(0), sess);*/
            save(bd, sess);
           /* bd.addTicket(new Ticket());*/
        }
        t.commit();
        sess.close();
        return bd;
    }


    public UserView getUserView(long userID, long ticketID) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным id
        User bd = get(userID, sess);
        List<UserAnswer> questions = (ArrayList<UserAnswer>) sess.createQuery("select ua FROM UserAnswer as ua where ticket=:tick order by id").setLong("tick", ticketID).list();
        Ticket tick = (Ticket) sess.get(Ticket.class, ticketID);
        t.commit();
        sess.close();

        UserAnswer cur = new UserAnswer();
        int cor = 0;
        int incor = 0;
        

        for (UserAnswer userAnswer : questions) {
            if (userAnswer.getAnswer() == null) {
                cur = userAnswer;
                break;
            } else {
                if (userAnswer.getAnswer().isCorrect()) {
                    cor++;
                } else {
                    incor++;
                }
            }
        }
        return new UserView(bd, 50, cur, cor, incor, tick.getTime());
    }

    public User getWithTicket(long userID, long ticketID) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным id
        User bd = get(userID, sess);
        List<Ticket> tickets = bd.getTickets();
        List<Ticket> ticketsNew = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            if (ticket.getId() == ticketID) {
                ticketsNew.add(ticket);
                break;
            }
        }
        t.commit();
        sess.close();



        bd.setTickets(ticketsNew);
        return bd;
    }

    private User get(long userID, Session sess) {
        return (User) sess.byId(User.class).load(userID);
    }

    private User get(String surname, String name, String patronomicname, long departmentID, Session sess) {
        return (User) sess.createQuery(
                "from User where deleted=0 and name=:name and surname=:surname and patronomicname=:patr and department.id=:dep")
                .setString("name", name)
                .setString("surname", surname)
                .setString("patr", patronomicname)
                .setLong("dep", departmentID)
                .uniqueResult();
    }
    
    private User get(long num, String numNum, Session sess) {
        return (User) sess.createQuery(
                "from User where deleted=0 and num=:num")
                .setLong("num", num)
                .uniqueResult();
    }


    private void save(User user, Session sess) {
        sess.saveOrUpdate(user);
    }

    public static void main(String[] args) {
        User withTicket = (new UserRepository()).getWithTicket(31, 31);
        String str = "sfd";
    }
}
