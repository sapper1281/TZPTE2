/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_InfoUpdatable;

/**
 *Службы. для БД под Хибернейт
 * @author VVolgina
 */
@Entity
@Table(name = "DEPARTMENT")
@SuppressWarnings("serial")
public class Department extends Data_InfoUpdatable implements Serializable{
    
    //<editor-fold defaultstate="collapsed" desc="поля">
    @Column(length=300, nullable=false)
    private String name="";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает название службы
     * @return название службы
     */
    public String getName() {
        return name;
    }
    
    /**
     * Назначает название службы
     * @param name название службы
     */
    public void setName(String name) {
        this.name = name;
    }


    //</editor-fold>
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id, списков и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Department obj) {
        super.cloneForDB(obj);
        name=obj.name;    
    }

    @Override
    public String toString() {
        return name ;
    }  
}
