/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import rzd.vivc.astzpte.beans.session.AutorizationBean;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.repository.DepartmentRepository;
import rzd.vivc.astzpte.repository.UserRepository;
import zdislava.common.security.users.AdminRepository;
import zdislava.common.security.users.Administrator;
import zdislava.common.security.users.ExistingUserExeption;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class AutorisationPageBean {

    //<editor-fold defaultstate="collapsed" desc="поля">
    private String FIO = "";
    private long departmentID = 0;
    @ManagedProperty(value = "#{autorizationBean}")
    private AutorizationBean bean;
    private List<Department> list;
    private String login = "";
    private String password = "";
    private Date birthday = null;
    private Date numDat = null;
    private Date allowDat = null;
    private Administrator usr = new Administrator();
    private long num;
    private String numNum = "";
    private String allowNum = "";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    public Date getNumDat() {
        return numDat;
    }

    public void setNumDat(Date numDat) {
        this.numDat = numDat;
    }

    public Date getAllowDat() {
        return allowDat;
    }

    public void setAllowDat(Date allowDat) {
        this.allowDat = allowDat;
    }

    /**
     * дата рождения пользователя
     *
     * @return дата рождения пользователя
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * дата рождения пользователя
     *
     * @param birthday дата рождения пользователя
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Возвращет список служб
     *
     * @return список служб
     */
    public List<Department> getList() {
        return list;
    }

    /**
     * Назначает список служб
     *
     * @param list список служб
     */
    public void setList(List<Department> list) {
        this.list = list;
    }

    /**
     * Get the value of FIO
     *
     * @return the value of FIO
     */
    public String getFIO() {
        return FIO;
    }

    /**
     * Set the value of FIO
     *
     * @param FIO new value of FIO
     */
    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    /**
     * Get the value of departmentID
     *
     * @return the value of departmentID
     */
    public long getDepartmentID() {
        return departmentID;
    }

    /**
     * Set the value of departmentID
     *
     * @param departmentID new value of departmentID
     */
    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public AutorizationBean getBean() {
        return bean;
    }

    public void setBean(AutorizationBean bean) {
        this.bean = bean;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getNumNum() {
        return numNum;
    }

    public void setNumNum(String numNum) {
        this.numNum = numNum;
    }

    public String getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(String allowNum) {
        this.allowNum = allowNum;
    }

    //</editor-fold>
    /**
     * Creates a new instance of AutorisationPageBean
     */
    public AutorisationPageBean() {
        loadInfo();
    }

    private void loadInfo() {
        try {
            list = new DepartmentRepository().getActiveDepartments();
        } catch (Exception e) {
            list = new ArrayList<Department>();
        }
    }

    /**
     * Осуществляет вход пользователя в систему
     *
     * @return error, при ошибке tickets или userentry, при успешном входе
     */
    public String logIn() {
        //пароль должен вводить только адимин
        if (!password.isEmpty()) {
            AdminRepository rep = new AdminRepository();
            //пытаемся найти в БД пользователя с указаными логином и паролем
            try {
                usr = rep.get(login, password);
                bean.setUsr(usr);
            } catch (ExistingUserExeption ex) {
                //не верные логин или пароль - вход запрещен    
                bean.setError(ex.getMessage());
                return "error";
            } catch (Exception e) {
                usr = new Administrator();
                login = "";
                password = "";
                bean.setError(e.getMessage());
                return "error";
            }
            return "adminentry";
        } else {
            //извлекаем отдельно фамилию, имя  и отчество пользователя
            User userUser = new User();

            userUser.setNum(num);

            try {
                //usrUser.setDepartment((new DepartmentRepository()).get(departmentID));
                userUser = (new UserRepository()).getUser(num, allowNum, numNum, allowDat, numDat);
                userUser.setAllowDat(allowDat);
                userUser.setNumDat(numDat);
                userUser.setNumNum(numNum);
                userUser.setAllowNum(allowNum);
            } catch (Exception e) {
                bean.setError(e.getMessage());
                bean.getCurrentUser().reset();
                return "error";
            }
            /*try {
             userUser.getTickets();*/
            bean.getCurrentUser().reset();
            bean.getCurrentUser().setUser(userUser);
            bean.getCurrentUser().setBeginDate(new Date());
            // bean.getCurrentUser().setTicket(userUser.getTickets().get(userUser.getTickets().size() - 2));
            return "userentry";
            /*} catch (LazyInitializationException ex) {
                
             bean.getCurrentUser().setUser(userUser);
             bean.getCurrentUser().setBeginDate(new Date());
             return "ticket";*/
            /*  } catch (Exception e) {
             bean.setError(e.getMessage());
             return "error";
             }*/
        }
    }

    public void change(ValueChangeEvent e) {
        FIO = "fddffddf";
    }
}
