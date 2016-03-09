/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import rzd.vivc.astzpte.dto.Answer;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.TicketTemplate;
import rzd.vivc.astzpte.repository.AnswerRepository;
import rzd.vivc.astzpte.repository.QuestionRepository;
import rzd.vivc.astzpte.repository.ThemeRepository;
import rzd.vivc.astzpte.repository.TicketTemplateRepository;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@ViewScoped
public class QBean implements Serializable {

    private String editText;
    private List<Theme> themes = new ArrayList<Theme>();
    private int themeID = 0;
     private int ticketID = 0;
    private Question qurrentQuestion = new Question();
    private List<TicketTemplate> ticketTemplates=new ArrayList<TicketTemplate>();
    //список вопросов имеющихся в теме
    private List<Question> questions = new ArrayList<Question>();
    //редактируемый ответ на вопрос
    private Answer ans = new Answer();

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public List<TicketTemplate> getTicketTemplates() {
        return ticketTemplates;
    }

    public void setTicketTemplates(List<TicketTemplate> ticketTemplates) {
        this.ticketTemplates = ticketTemplates;
    }

    
    
    /**
     * редактируемый ответ на вопрос
     *
     * @return редактируемый ответ на вопрос
     */
    public Answer getAns() {
        return ans;
    }

    /**
     * редактируемый ответ на вопрос
     *
     * @param ans редактируемый ответ на вопрос
     */
    public void setAns(Answer ans) {
        this.ans = ans;
    }

    /**
     * список вопросов имеющихся в теме
     *
     * @return список вопросов имеющихся в теме
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * список вопросов имеющихся в теме
     *
     * @param questions список вопросов имеющихся в теме
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getEditText() {
        return qurrentQuestion.getText();
    }

    public void setEditText(String editText) {

        qurrentQuestion.setText(editText);
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public int getThemeID() {
        return themeID;
    }

    public void setThemeID(int themeID) {
        this.themeID = themeID;
        for (Theme theme : themes) {
            if (theme.getId() == themeID) {
                //  qurrentQuestion.setTheme(theme);
                break;
            }
        }
        /*   if (qurrentQuestion.getTheme() == null) {
         qurrentQuestion.setTheme(new Theme());
         }*/
    }

    public Question getQurrentQuestion() {
        return qurrentQuestion;
    }

    public void setQurrentQuestion(Question qurrentQuestion) {
        this.qurrentQuestion = qurrentQuestion;
    }

    public void saveQuestion() {
        if (qurrentQuestion.getId() == 0) {
            questions.add(qurrentQuestion);
        }
        new QuestionRepository().save(qurrentQuestion);
    }

    public void geberateQuestionList() {
        questions = new QuestionRepository().getQuestionsForTheme(ticketID);
    }

    public void generateTicketList() {
        ticketTemplates=new TicketTemplateRepository().getActiveTicketTemplates(themeID);
    }

    public void newQuest() {
        qurrentQuestion = new Question();
        /* qurrentQuestion.setTheme(new ThemeRepository().get(themeID));*/
        long max = 0;
        for (Question question : questions) {
            if (question.getId() > max) {
                max = question.getId();
            }
        }
        qurrentQuestion.setTemp_id(max > questions.size() ? max + 1 : questions.size() + 1);
        // qurrentQuestion.setUser();
    }

    public void oldQuest(long id) {
        for (Question q : questions) {
            if (q.getTemp_id() == id) {
                qurrentQuestion = q;
            }
        }
    }

    public void delQuest(long id) {
        Question toRemove = null;
        for (Question q : questions) {
            if (q.getTemp_id() == id) {
                q.setDeleted(true);
                new QuestionRepository().save(q);
                toRemove = q;
            }
        }
        questions.remove(toRemove);
    }

    public void newAns() {
        ans = new Answer();
        ans.setQuestion(qurrentQuestion);
        qurrentQuestion.getAnswers().add(ans);
        long max = 0;
        for (Answer question : qurrentQuestion.getAnswers()) {
            if (question.getId() > max) {
                max = question.getId();
            }
        }
        ans.setTemp_id(max > qurrentQuestion.getAnswers().size() ? max + 1 : qurrentQuestion.getAnswers().size() + 1);
    }

    public void oldAns(long id) {
        for (Answer a : qurrentQuestion.getAnswers()) {
            if (a.getTemp_id() == id) {
                ans = a;
            }
        }
    }

    public void delAns(long id) {
        Answer toRemove = null;
        for (Answer q : qurrentQuestion.getAnswers()) {
            if (q.getTemp_id() == id) {
                q.setDeleted(true);
                toRemove = q;
            }
        }
        if (toRemove != null) {
            if (toRemove.getId() != 0) {
                new AnswerRepository().delete(toRemove);
            }
            qurrentQuestion.getAnswers().remove(toRemove);
        }
    }

    /**
     * Проверяет, создан ли для данного вопроса правильный ответ
     *
     * @return true, если создан
     */
    public boolean checkAnswersExists() {

        List<Answer> answers = qurrentQuestion.getAnswers();
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new instance of QBean
     */
    public QBean() {
        themes = new ThemeRepository().getActiveThemes();
    }
}
