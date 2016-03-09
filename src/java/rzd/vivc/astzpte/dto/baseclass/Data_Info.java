/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto.baseclass;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Базовый класс для классов под хибернейт
 *
 * @author apopovkin
 */
@MappedSuperclass
public class Data_Info implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Поля">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "dt_end")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dt_end;
    @Column(name = "dt_create", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dt_create = new Date();
    @Column(name = "del_fl")
    private boolean deleted=false;
    @Transient
    private long temp_id=0;
    
    
    //<editor-fold defaultstate="collapsed" desc="get-set">
    
    
    /**
     * Возвращает id. Генерируется автоматически
     *
     * @return id
     */
    //</editor-fold>

    public long getTemp_id() {
        return id==0?temp_id:id;
    }

    public void setTemp_id(long temp_id) {
        this.temp_id = temp_id;
    }
    
    
    /**
     * Возвращает id. Генерируется автоматически
     *
     * @return id
     */
    public long getId() {
        return id;
    }
    
    /**
     * Назначает id. Генерируется автоматически
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    
    /**
     * Извлекает дату окончания актуальности записи
     *
     * @return Извлекает дату окончания актуальности записи
     */
    public Date getDt_end() {
        return dt_end;
    }
    
    /**
     * Добавляет дату окончания актуальности записи
     *
     * @param dt_end Добавляет дату окончания актуальности записи
     */
    public void setDt_end(Date dt_end) {
        this.dt_end = dt_end;
    }
    
    /**
     * Извлекает дату создания. Назначить ее нельзя. Устанавливается при создании объекта текущим временем.
     * При клонировании объета для БД не переносится, т.е. сохраняется та, что была у объета из БД
     *
     * @return Извлекает дату создания
     */
    public Date getDt_create() {
        return dt_create;
    }
    
    /**
     * Добавить дату создания
     *
     * @param dt_create Добавить дату создания
     */
    public void setDt_create(Date dt_create) {
        this.dt_create = dt_create;
    }
    
    /**
     * Возвращает индикатор удаления объекта
     * @return индикатор удаления объекта
     */
    public boolean isDeleted() {
        return deleted;
    }
    
    /**
     * Назначает индикатор удаления объекта
     * @param deleted индикатор удаления объекта
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    //</editor-fold>
    
    
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(Data_Info obj) {
        dt_end=obj.dt_end;
        deleted=obj.deleted;      
    }

    @Override
    public String toString() {
        return ", dt_end=" + dt_end + ", dt_create=" + dt_create + ", deleted=" + deleted +"id=" + id;
    }
}
