/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.TicketTemplate;
import zdislava.common.dto.configuration.SessionFactorySingleton;
import rzd.vivc.astzpte.model.ThemeWithQuestionCount;

/**
 *
 * @author apopovkin
 */
public class TicketTemplateRepository {

    /**
     * Добавление новой/редактирование темы
     *
     * @param elem тема
     */
    public void save(TicketTemplate elem) {

        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        sess.saveOrUpdate(elem);

        t.commit();
        sess.close();
    }

    /**
     * Извлекает из БД информацию о теме
     *
     * @param id id
     * @return темы
     */
    public TicketTemplate get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД тему с id
        TicketTemplate bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }

    protected TicketTemplate get(long id, Session sess) {
        return (TicketTemplate) sess.get(TicketTemplate.class, id);
    }

    public TicketTemplate getWithQuestions(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД тему с id
        TicketTemplate bd = (TicketTemplate) sess.createQuery(
                "select t from TicketTemplate as t LEFT JOIN FETCH t.questions as q WHERE t.id=:id order by q.num").setLong("id", id).uniqueResult();

        t.commit();
        sess.close();

        return bd;
    }

    /**
     * Извлекает из БД весь список неудаленных тем, отсортированный по названию
     *
     * @return список тем
     */
    public List<TicketTemplate> getActiveTicketTemplates(long themeID) {
        List<TicketTemplate> dbList;

        // извлекаем список неудаленных тем отсортированный по имени
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<TicketTemplate>) sess.createQuery(
                "select t from TicketTemplate as t where t.deleted=0 and t.theme.id=:id ORder by t.num").setLong("id", themeID).list();
        t.commit();
        sess.close();

        return dbList;
    }

    /**
     * Извлекает из БД весь список неудаленных тем, с к-вом вопросов в них
     *
     * @return список тем
     */
    public List<ThemeWithQuestionCount> getActiveTicketTemplatesCount(long themeID) {
        List<ThemeWithQuestionCount> dbList;

        // извлекаем список неудаленных тем отсортированный по имени
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<ThemeWithQuestionCount>) sess.createQuery(
                "select new rzd.vivc.astzpte.model.ThemeWithQuestionCount(t.id, t.num, count(q.id)) from TicketTemplate as t join t.questions as q where t.deleted=0 and q.deleted=0 and t.theme.id=:id group by t.id, t.num").setLong("id", themeID).list();
        t.commit();
        sess.close();

        return dbList;
    }

    public static void main(String[] args) {
        TicketTemplateRepository templateRepository = new TicketTemplateRepository();
       /* for (long i = 2; i <= 8; i++) {
            for (int j = 1; j <= 10; j++) {
                TicketTemplate template = new TicketTemplate();
                template.setNum(j);
                template.setTheme(new Theme(i));
                templateRepository.save(template);
            }
        }*/
    /*    List<ThemeWithQuestionCount> activeTicketTemplatesCount = templateRepository.getActiveTicketTemplatesCount(1);
        for (ThemeWithQuestionCount activeTicketTemplatesCount1 : activeTicketTemplatesCount) {
            System.out.println(activeTicketTemplatesCount1.getNum());
        }*/

        TicketTemplate withQuestions = templateRepository.getWithQuestions(1);
        List<Question> questions = withQuestions.getQuestions();
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
