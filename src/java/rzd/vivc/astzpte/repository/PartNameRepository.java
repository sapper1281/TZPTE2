/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zdislava.common.dto.configuration.SessionFactorySingleton;
import zdislava.common.vocabulary.PartName;

/**
 * Репозиторий для разделов словаря. под Хибер
 *
 * @author VVolgina
 */
public class PartNameRepository {

    /**
     *
     * Добавление нового/редактирование раздела словаря
     *
     * @param elem служба
     */
    public void save(PartName elem) {

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
     * @return раздел словаря
     */
    public PartName get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД раздел
        PartName bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }
    

    private PartName get(long id, Session sess) {
        return (PartName) sess.createQuery("from PartName as part left join part.vocabulary_values where id=:id").setLong("id", id).uniqueResult();
    }

    protected PartName get(String name, Session sess) {
        return (PartName) sess.createQuery("from PartName where name=:name").setString("name", name).uniqueResult();    
    }
    
    public PartName get(String name) {
        
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        PartName res= (PartName) sess.createQuery("from PartName where name=:name").setString("name", name).uniqueResult();    

        t.commit();
        sess.close();

        return res;
    }
    
    /**
     * Извлекает из БД весь список неудаленных раздел, отсортированный по
     * названию
     *
     * @return список пользователей
     */
    public List<PartName> getActivePartNames() {
        List<PartName> dbList;

        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        dbList = (List<PartName>) sess.createQuery(
                "from PartName where deleted=0 order by name").list();
        t.commit();
        sess.close();

        return dbList;
    }
   
}
