/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.model.UserView;
import zdislava.common.dto.configuration.SessionFactorySingleton;

/**
 *
 * @author VVolgina
 */
public class UserAnswerRepository {

    protected void save(UserAnswer elem, Session sess) {
        sess.saveOrUpdate(elem);
    }

    public void save(UserAnswer elem) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        save(elem, sess);
        t.commit();
        sess.close();

    }
}
