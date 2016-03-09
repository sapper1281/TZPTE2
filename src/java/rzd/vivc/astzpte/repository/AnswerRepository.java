/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.dto.Answer;
import zdislava.common.dto.configuration.SessionFactorySingleton;

/**
 *
 * @author apopovkin
 */
public class AnswerRepository {
    /**
     * Добавление новой/редактирование службы
     *
     * @param elem служба
     */
    public void save(Answer elem){

        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
       
            sess.saveOrUpdate(elem);
        
        t.commit();
        sess.close();
    }

    /**
     * Извлекает из БД информацию о службе
     *
     * @param id id
     * @return служба
     */
    public Answer get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным логином
        Answer bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }

    protected Answer get(long id, Session sess) {
        return (Answer) sess.get(Answer.class, id);
    }

    /**
     * Извлекает из БД весь список неудаленных служб, отсортированный по
     * названию
     *
     * @return список пользователей
     */
    public List<Answer> getActiveDepartments() {
        List<Answer> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<Answer>) sess.createQuery(
                "from Answer where deleted=0 order by text").list();
        t.commit();
        sess.close();

        return dbList;
    } 
    
     public void delete(Answer ans) {
       
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
       sess.delete(ans);
        t.commit();
        sess.close();

    } 
}
