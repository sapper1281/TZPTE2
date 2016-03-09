/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zdislava.common.vocabulary;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;

/**
 * Значение из словаря. Они сгрупированы по разделам PartName. Для БД. Под
 * Хибер.
 *
 * @author VVolgina
 */
@Entity
@Table(name = "VocabularyValie")
@SuppressWarnings("serial")
public class VocabularyValue extends Data_Info implements Serializable{
    //<editor-fold defaultstate="collapsed" desc="поля">

    @Column(length = 10000, nullable = true)
    private String text = "";
    @ManyToOne
    @JoinColumn(name="part_name")
    private PartName part_name = new PartName();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
    /**
     * Возвращает значение из словаря.
     * @return значение из словаря
     */
    public String getText() {
        return text;
    }
    
    /**
     * Назначает значение из словаря
     * @param text значение из словаря
     */
    public void setText(String text) {
        this.text = text;
    }
       
    /**
     * Возвращает раздел словаря, которому принадлежит значение
     * @return раздел словаря, которому принадлежит значение
     */
    public PartName getPart_name() {
        return part_name;
    }
    
    /**
     * Назначает раздел словаря, которому принадлежит значение
     * @param part_name раздел словаря, которому принадлежит значение
     */
    public void setPart_name(PartName part_name) {
        this.part_name = part_name;
    }
    //</editor-fold>
    
    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id и даты создания
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(VocabularyValue obj) {
        part_name=obj.part_name;
        text=obj.text;
    }

    @Override
    public String toString() {
        return "VocabularyValue{" + "text=" + text + ", part_name=" + part_name.getName() + '}';
    }
}
