/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Department;
import zdislava.common.dto.configuration.SessionFactorySingleton;
import zdislava.common.vocabulary.PartName;

/**
 * Репозиторий для отделов.
 * @author VVolgina
 */
public class DepartmentRepository {
    /**
     * Добавление новой/редактирование службы
     *
     * @param elem служба
     */
    public void save(Department elem){

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
    public Department get(long id) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным логином
        Department bd = get(id, sess);

        t.commit();
        sess.close();

        return bd;
    }

    protected Department get(long id, Session sess) {
        return (Department) sess.get(Department.class, id);
    }

    /**
     * Извлекает из БД весь список неудаленных служб, отсортированный по
     * названию
     *
     * @return список пользователей
     */
    public List<Department> getActiveDepartments() {
        List<Department> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<Department>) sess.createQuery(
                "from Department where deleted=0 order by name").list();
        t.commit();
        sess.close();

        return dbList;
    }
    
    
    
    
    
    
      public static void main(String[] args) {
        DepartmentRepository drep = new DepartmentRepository();
        Department dep=new Department();
        dep.setName("Д");
        drep.save(dep);
    } 
}
