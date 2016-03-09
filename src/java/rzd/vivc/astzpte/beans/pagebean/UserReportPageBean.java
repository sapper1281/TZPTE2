/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.model.UserAnswerModel;
import rzd.vivc.astzpte.model.UserModel;
import rzd.vivc.astzpte.repository.PartNameRepository;
import rzd.vivc.astzpte.repository.TicketRepository;
import rzd.vivc.astzpte.repository.UserRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class UserReportPageBean  implements Serializable{

    @ManagedProperty(value = "#{autorizationBean.currentUser}")
    private UserModel currentUser;
    private User usr = new User();
    private List<UserAnswerModel> questions;
    private int correct;
    private int incorrect;
    private Ticket tick = new Ticket();
    private int max;

    public Ticket getTick() {
        return tick;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public List<UserAnswerModel> getQuestions() {
        return questions;
    }

    public int getCorrect() {
        return correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public int getAnswered() {
        return incorrect + correct;
    }

    public int getNonAnswered() {
        return 50 - getAnswered();
    }

    public int getTotal() {
        return max;
    }

    public int countIncorect() {
        int i = 0;
        for (UserAnswerModel userAnswer : questions) {
            if (userAnswer.getAnswer() != null && !userAnswer.getAnswer().isCorrect()) {
                i++;
            }
        }
        return i;
    }

    public int countCorect() {
        int i = 0;
        for (UserAnswerModel userAnswer : questions) {
            if (userAnswer.getAnswer() != null && userAnswer.getAnswer().isCorrect()) {
                i++;
            }
        }
        return i;
    }

    public int getRemain() {
        return max - tick.getTime();
    }

    /**
     * Creates a new instance of UserReportPageBean
     */
    public UserReportPageBean() {

    }

    private String link="";

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
    @PostConstruct
    public void init() {
        usr = (new UserRepository()).getWithTicket(currentUser.getUserID(), currentUser.getTicketID());
        List<UserAnswer> answers = usr.getTickets().get(0).getAnswers();
        questions = new ArrayList<UserAnswerModel>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getAnswer() != null) {
                questions.add(new UserAnswerModel(answers.get(i), i));
            }
        }
        correct = countCorect();
        incorrect = countIncorect();
        tick = usr.getTickets().get(0);
        max = 50;
        ReportBean reportBean=new ReportBean();
        link=reportBean.generateReport(usr);
    }
}
