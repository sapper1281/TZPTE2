/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.TicketTemplate;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;

/**
 * В классе собраны методы, необходимые для проверки наличия незавершенных
 * билетов и генерации новых;
 *
 * @author VVolgina
 */
public class TicketGenerator {

    //<editor-fold defaultstate="collapsed" desc="Поля">
    private User user = new User();
    //</editor-fold>

    public TicketGenerator(User user) {
        this.user = user;
    }

    public TicketGenerator() {
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
    //</editor-fold>

    public boolean isNeedToGenerateNewTicket() {
        List<Ticket> tickets = user.getTickets();
        if (tickets == null || tickets.isEmpty()) {
            return true;
        }
        for (Ticket ticket : tickets) {
            if (!isTicketFinished(ticket)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTicketFinished(Ticket tick) {
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

    public void removeFinishedTickets() {
        List<Ticket> res = new ArrayList<Ticket>();
        List<Ticket> tickets = user.getTickets();
        for (Ticket ticket : tickets) {
            if (!isTicketFinished(ticket)) {
                res.add(ticket);
            }
        }
        user.setTickets(res);
    }

    public void generateNewTicket(List<Question> questions, int count) throws IllegalArgumentException {
        Random random = new Random();
        int size = questions.size();
        if (count > size) {
            throw new IllegalArgumentException("Недостаточное количество вопросов для генерации билета. Обратитесь к составителю заданий.");
        }

        List<Question> safeQuestions = new ArrayList<Question>();
        safeQuestions.addAll(questions);

        Ticket tick = new Ticket();

        if (count == size) {
            tick.setQuestions(safeQuestions);
        } else {

            List<Question> res = new ArrayList<Question>();

            for (int i = 0; i < count; i++) {
                int num = random.nextInt(size);
                res.add(safeQuestions.get(num));
                safeQuestions.remove(num);
                size--;
            }

            tick.setQuestions(res);
        }

        user.addTicket(tick);
    }

    public void generateNewTicket(TicketTemplate ticketTemplate) {
        Ticket tick = new Ticket();
        tick.setQuestions(ticketTemplate.getQuestions());
         user.addTicket(tick);
    }
}
