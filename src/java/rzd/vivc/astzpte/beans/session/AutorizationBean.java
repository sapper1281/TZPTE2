/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.session;

import java.io.Serializable;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.model.UserModel;
import rzd.vivc.astzpte.repository.UserRepository;
import zdislava.common.security.users.AdminRepository;
import zdislava.common.security.users.Administrator;
import zdislava.common.security.users.ExistingUserExeption;

/**
 * Сессионный бин. Хранится информация о пользователе и его правах на доступ к
 * страницам
 *
 * @author VVolgina
 */
@ManagedBean
@SessionScoped
public class AutorizationBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="поля">
    private Administrator usr = new Administrator();
    private UserModel currentUser = new UserModel();
    private String error = "";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает сообщение о произошедшей ошибке
     *
     * @return сообщение о произошедшей ошибке
     */
    public String getError() {
        return error;
    }

    /**
     * Назначает сообщение о произошедшей ошибке
     *
     * @param error сообщение о произошедшей ошибке
     */
    public void setError(String error) {
        this.error = error;
    }

    public Administrator getUsr() {
        return usr;
    }

    public void setUsr(Administrator usr) {
        this.usr = usr;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    //</editor-fold>
    /**
     * Creates a new instance of AutorizationBean
     */
    public AutorizationBean() {
    }

    

    public String logOut() {
        error = "";
        usr = new Administrator();
        currentUser = new UserModel();
        return "userentry";
    }
}
