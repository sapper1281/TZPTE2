/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.dto.Theme;
import zdislava.common.dto.configuration.SessionFactorySingleton;
import rzd.vivc.astzpte.model.ThemeWithQuestionCount; 

/**
 *
 * @author apopovkin
 */
public class ThemeRepository {
    /**
     * Добавление новой/редактирование темы
     *
     * @param elem тема
     */
    public void save(Theme elem){

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
    public Theme get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД тему с id
        Theme bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }

    protected Theme get(long id, Session sess) {
        return (Theme) sess.get(Theme.class, id);
    }

    /**
     * Извлекает из БД весь список неудаленных тем, отсортированный по
     * названию
     *
     * @return список тем
     */
    public List<Theme> getActiveThemes() {
        List<Theme> dbList;
        
        // извлекаем список неудаленных тем отсортированный по имени
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<Theme>) sess.createQuery(
                "from Theme where deleted=0 order by id").list();
        t.commit();
        sess.close();

        return dbList;
    } 
    
        /**
     * Извлекает из БД весь список неудаленных тем, с к-вом вопросов в них
     *
     * @return список тем
     */
    public List<ThemeWithQuestionCount> getActiveThemesCount() {
        List<ThemeWithQuestionCount> dbList;
        
        // извлекаем список неудаленных тем отсортированный по имени
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<ThemeWithQuestionCount>) sess.createQuery(
                "select new rzd.vivc.astzpte.model.ThemeWithQuestionCount(t.id, t.name, count(q.id)) from Theme as t join t.ticketTemplates as q where t.deleted=0 and q.deleted=0 group by t.id, t.name").list();
        t.commit();
        sess.close();

        return dbList;
    } 
    
    public static void main(String[] args) {
        ThemeRepository themeRepository = new ThemeRepository();
        
        List<ThemeWithQuestionCount> activeThemesCount = themeRepository.getActiveThemesCount();
        for (ThemeWithQuestionCount themeWithQuestionCount : activeThemesCount) {
            System.out.println(themeWithQuestionCount);
        }
    } 
}
