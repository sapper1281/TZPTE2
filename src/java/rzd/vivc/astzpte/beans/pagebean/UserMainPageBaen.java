/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import rzd.vivc.astzpte.beans.session.AutorizationBean;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Answer;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.model.UserView;
import rzd.vivc.astzpte.repository.PartNameRepository;
import rzd.vivc.astzpte.repository.QuestionRepository;
import rzd.vivc.astzpte.repository.TicketRepository;
import rzd.vivc.astzpte.repository.UserAnswerRepository;
import rzd.vivc.astzpte.repository.UserRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@ViewScoped
public class UserMainPageBaen  implements Serializable{

    @ManagedProperty(value = "#{autorizationBean}")
    private AutorizationBean session;
    private UserView user = new UserView();
    private long givenAnswer = 0;
    private int remain = 600;
    private boolean notStoped = true;
    private int max;

    public int getRemain() {
        return remain;
    }

    public AutorizationBean getSession() {
        return session;
    }

    public void setSession(AutorizationBean session) {
        this.session = session;
    }

    public User getUser() {
        return user.getUser();
    }

    public void setUser(User user) {
        this.user.setUser(user);
    }

    public int getCorrect() {
        return user.getCorrect();
    }

    public void setCorrect(int correct) {
        user.setCorrect(correct);
    }

    public int getIncorrect() {
        return user.getIncorrect();
    }

    public void setIncorrect(int incorrect) {
        user.setIncorrect(incorrect);
    }

    public int getTotal() {
        return user.getQuestionCount();
    }

    public Question getCurrentQuestion() {
        return user.getCurrentQuestion().getQuestion();
    }

    public int getAnswered() {
        return getCorrect() + getIncorrect();
    }

    public long getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(long givenAnswer) {
        this.givenAnswer = givenAnswer;
    }



    /**
     * Creates a new instance of UserMainPageBaen
     */
    public UserMainPageBaen() {
    }

    @PostConstruct
    public void init() {
        UserRepository rep = new UserRepository();
        if (session.getCurrentUser().getTicketID() == 0) {
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("ticket")) {
                
                long userID = session.getCurrentUser().getUserID();
                Long aLong = new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ticket"));
                Ticket createNewTicket = new TicketRepository().createNewTicket(userID, aLong);
                session.getCurrentUser().setTicketID(createNewTicket.getId());
            }
        }
        user = rep.getUserView(session.getCurrentUser().getUserID(), session.getCurrentUser().getTicketID());
        remain = max - user.getRemain();
    }

    public String nextQuestion() {
        for (Answer answer : user.getCurrentQuestion().getQuestion().getAnswers()) {
            if (answer.getId() == givenAnswer) {
                user.getCurrentQuestion().setAnswer(answer);
                if (answer.isCorrect()) {
                    user.setCorrect(user.getCorrect() + 1);
                } else {
                    user.setIncorrect(user.getIncorrect() + 1);
                }
                (new UserAnswerRepository()).save(user.getCurrentQuestion());
                break;
            }
        }
        if (getAnswered() < getTotal()) {
            user.setCurrentQuestion((new QuestionRepository()).getNextQuestion(user.getCurrentQuestion().getId(), session.getCurrentUser().getTicketID()));
            return "";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Вопросы закончились", ""));
        //останавливаем отсчет времени
        notStoped = false;
        new TicketRepository().updateTime( session.getCurrentUser().getTicketID());
        return "";
    }
}
