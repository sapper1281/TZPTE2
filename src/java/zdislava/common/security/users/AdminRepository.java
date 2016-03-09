package zdislava.common.security.users;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zdislava.common.dto.configuration.SessionFactorySingleton;

/**
 * Нужен для сохранения - извлечения из БД объектов, связанных с пользователями
 * и правами
 *
 * @author VVolgina
 *
 */
public class AdminRepository {

    /**
     * Добавление нового админа
     *
     * @param elem пользователь
     * @throws ExistingAdminExeption если пользователь с таким логином уже
     * существует
     * @throws IllegalArgumentException если у пользователя не указан логин или
     * пароль
     */
    public void addNewAdmin(Administrator elem) throws ExistingUserExeption,
            IllegalArgumentException {
        // проверка, что у добавляемого пользователя введены логин и пароль
        if (elem.getLogin().isEmpty() || elem.getLogin() == null
                || elem.getPassword().isEmpty() || elem.getPassword() == null) {
            throw new IllegalArgumentException(
                    "Необходимо указать логин и пароль");
        }

        // пробуем извлечь из БД пользователя с данным логином
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        Administrator bd = get(elem.getLogin(), sess);
        // Если не извлекся - создаем нового пользователя
        // если такой пользователь уже существует, выдаем ошибку
        if (bd == null) {
            sess.persist(elem);
        }
        t.commit();
        sess.close();

        // если такой пользователь уже существует, выдаем ошибку
        if (bd != null) {
            throw new ExistingUserExeption(
                    "Пользователь с данным именем уже существует");
        }
    }

    /**
     * Изменение информации о пользователе. Идентификация по логину, а не по id
     *
     * @param elem пользователь
     * @throws ExistingAdminExeption отсутствует пользователь с указаным логином
     * @throws IllegalArgumentException если у пользователя не указан логин или
     * пароль
     */
    public void changeExistingAdmin(Administrator elem) throws ExistingUserExeption,
            IllegalArgumentException {
        // проверка, что у изменяемого пользователя введены логин и пароль
        if (elem.getLogin().isEmpty() || elem.getLogin() == null
                || elem.getPassword().isEmpty() || elem.getPassword() == null) {
            throw new IllegalArgumentException(
                    "Необходимо указать логин и пароль");
        }

        // пробуем извлечь из БД пользователя с данным логином
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        Administrator bd = get(elem.getLogin(), sess);
        // если такой пользователь уже существует,обновляем информацию о нем
        if (bd != null) {
            bd.cloneForDB(elem);
            sess.update(bd);
        }
        t.commit();
        sess.close();

         // Если не извлекся - выдаем ошибку
        if (bd == null) {
            throw new ExistingUserExeption(
                    "Пользователь с данным именем не существует");
        }
    }

    /**
     * Извлекает из БД информацию о пользователи, при условии, что логин и
     * пароль введены верно
     *
     * @param login логин
     * @param password пароль
     * @return пользователь
     * @throws ExistingUserExeption если не совпадает логин или пароль
     */
    public Administrator get(String login, String password)
            throws ExistingUserExeption {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // пытаемся извлечь из БД пользователя с данным логином
        Administrator bd = get(login, sess);
        t.commit();
        sess.close();
        
        // если такого нет, Неверно указан пароль или пользователь заблокирован - выдаем ошибку
        if (bd == null) {
            throw new ExistingUserExeption(
                    "Пользователь с данным логином не существует");
        }

        if (!bd.getPassword().equals(password)) {
            throw new ExistingUserExeption(
                    "Введен неверный пароль");
        }

        if (bd.isDeleted()) {
            throw new ExistingUserExeption(
                    "Пользователь заблокирован");
        }

        return bd;
    }

    /**
     * Извлекает из БД информацию о пользователи
     *
     * @param login логин
     * @return пользователь
     */
    public Administrator get(String login) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        // пытаемся извлечь из БД пользователя с данным логином
        Administrator bd = get(login, sess);

        t.commit();
        sess.close();

        return bd;
    }

    private Administrator get(String login, Session sess) {
        return (Administrator) sess.bySimpleNaturalId(Administrator.class).load(login);
    }

    /**
     * Извлекает из БД весь список неудаленных пользователей, отсортированный по
     * фамилии
     *
     * @return список пользователей
     */
    public List<Administrator> getActiveAdmins() {
        List<Administrator> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<Administrator>) sess.createQuery(
                "from Admin where deleted=0 order by surname").list();
        t.commit();
        sess.close();

        return dbList;
    }
}
