/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zdislava.common.security.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;

/**
 * Люди. Анотации под хибер. ОТ него унаследованы юзеры(билеты) и админы
 *
 * @author VVolgina
 */
@MappedSuperclass
public class Person extends Data_Info implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="Поля">
    @Column(name = "NAME")
    private String name = "";
    @Column(name = "SURNAME")
    private String surname = "";
    @Column(name = "PATRONOMICNAME")
    private String patronomicname = "";

    //</editor-fold>
    public Person() {
    }

    public Person(String name, String surname, String patronomicname) {
        this.name=name;
        this.surname=surname;
        this.patronomicname=patronomicname;
    }
    
    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * возвращвет имя пользователя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }
    
    /**
     * назначает имя пользователя
     *
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * возвращает фамилию пользователя
     *
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * назначает фамилию пользователя
     *
     * @param surname фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * возвращает отчество пользователя
     *
     * @return отчество
     */
    public String getPatronomicname() {
        return patronomicname;
    }
    
    /**
     * назначает отчество пользователя
     *
     * @param patronomicname отчество
     */
    public void setPatronomicname(String patronomicname) {
        this.patronomicname = patronomicname;
    }
    
    /**
     * Возвращает ФИО пользователя
     *
     * @return ФИО
     */
    public String getFIO() {
        return surname + " " + name + " " + patronomicname;
    }
    //</editor-fold>
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Person obj) {
        super.cloneForDB(obj);
        name=obj.name;
        patronomicname=obj.patronomicname;
        surname=obj.surname;
    }

    @Override
    public String toString() {
        return "name=" + name + ", surname=" + surname + ", patronomicname=" + patronomicname + super.toString();
    }
    
    
}
