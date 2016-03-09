/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto.baseclass;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import zdislava.common.security.users.Administrator;

/**
 * Базовый класс для классов под хибернейт, для которых важно, кто и когда менял
 * запись
 *
 * @author VVolgina
 */
@MappedSuperclass
public class Data_InfoUpdatable extends Data_Info {

    //<editor-fold defaultstate="collapsed" desc="поля">
    @Column(name = "dt_update", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dt_update = new Date();
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=true)
    private Administrator user;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает дату последнего обновления. Проставляется автоматически
     * текущим временем при создании объекта. При обновлении в БД идет дата
     * нового объекта.
     *
     * @return дата последнего обновления
     */
    public Date getDt_update() {
        return dt_update;
    }
    
    /**
     * Возвращает пользователя последним редактировавшего запись
     *
     * @return пользователь последним редактировавший запись
     */
    public Administrator getUser() {
        return user;
    }
    
    /**
     * Назначает пользователя последним редактировавшего запись
     *
     * @param user пользователь последним редактировавшего запись
     */
    public void setUser(Administrator user) {
        this.user = user;
    }
    //</editor-fold>
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Data_InfoUpdatable obj) {
        super.cloneForDB(obj);
        dt_update=obj.dt_update;
        user=obj.user;
    }

    
    
}
