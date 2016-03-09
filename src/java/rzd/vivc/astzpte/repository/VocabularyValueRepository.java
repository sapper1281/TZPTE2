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
import zdislava.common.vocabulary.VocabularyValue;

/**
 * Репозиторий для значений словаря
 *
 * @author VVolgina
 */
public class VocabularyValueRepository {
    
    /**
     *
     * Добавление нового/редактирование Значения словаря
     *
     * @param elem сзначение
     */
    public void save(VocabularyValue elem) {

        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        
        elem.setPart_name((PartName)sess.byId( PartName.class ).getReference( elem.getPart_name().getId() ));
        
        sess.saveOrUpdate(elem);

        t.commit();
        sess.close();
    }

    /**
     * Извлекает из БД информацию о значении словаря
     *
     * @param id id
     * @return значение словаря
     */
    public VocabularyValue get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        VocabularyValue bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }

    private VocabularyValue get(long id, Session sess) {
        return (VocabularyValue) sess.get(VocabularyValue.class, id);
    }
    
     /**
     * Извлекает из БД весь список неудаленных значений раздела, отсортированный по
     * id
     *
     * @return список Значений
     */
    public List<VocabularyValue> getActiveValues(long partNameID) {
        List<VocabularyValue> dbList;

        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        
        dbList = (List<VocabularyValue>) sess.createQuery(
                "from VocabularyValue where deleted=0 and part_name=:pid order by id").setLong("pid", partNameID).list();
        
        t.commit();
        sess.close();

        return dbList;
    }
    
     
}
