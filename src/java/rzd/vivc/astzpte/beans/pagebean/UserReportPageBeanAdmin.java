/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.model.UserAnswerModel;
import rzd.vivc.astzpte.repository.PartNameRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class UserReportPageBeanAdmin implements Serializable {

    @ManagedProperty(value = "#{usersBean}")
    private UsersBean usersBean;
    private List<UserAnswerModel> questions;
    private int correct;
    private int incorrect;
    private Ticket tick = new Ticket();
    private int max;
    private int max_q;
 

    public Ticket getTick() {
        return tick;
    }

    public UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(UsersBean usersBean) {
        this.usersBean = usersBean;
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
        return max_q - getAnswered();
    }

    public int getTotal() {
        return max_q;
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
    public UserReportPageBeanAdmin() {
    }

    @PostConstruct
    public void init() {
        tick = usersBean.getCurrentTicket();
        if (tick != null) {
            
        List<UserAnswer> answers = tick.getAnswers();
            questions = new ArrayList<UserAnswerModel>();
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).getAnswer() != null) {
                    questions.add(new UserAnswerModel(answers.get(i), i));
                }
            }
            correct = countCorect();
            incorrect = countIncorect();
            max_q = 50;
        }
    }
}
