package zdislava.common.security.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

/**
 * Описание Администратора системы. ФИО, логин-пароль, Для БД. Анотации под хибернейт
 *
 * @author Zdislava
 *
 */
@Entity
@Table(name = "Admin")
@SuppressWarnings("serial")
public class Administrator extends Person  implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="поля">
    @NaturalId
    @Column(name = "LOGIN")
    private String login = "";
    @Column(name = "PASSWORD")
    private String password = "";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * возвращает логин
     *
     * @return логин
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * назначает логин
     *
     * @param login логин
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /**
     * возвращает пароль
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * назначает пароль
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>

   /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Administrator obj) {
        super.cloneForDB(obj);
        login=obj.login;
        password=obj.password;
    }

    @Override
    public String toString() {
        return "Admin{" + "login=" + login + ", password=" + password + super.toString()+'}';
    }
}
