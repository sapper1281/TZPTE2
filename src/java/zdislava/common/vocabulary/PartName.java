/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zdislava.common.vocabulary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import rzd.vivc.astzpte.dto.baseclass.Data_Info;

/**
 * Название раздела справочника. Для БД. Под Хибернейт
 *
 * @author VVolgina
 */
@Entity
@Table(name = "PartName")
@SuppressWarnings("serial")
public class PartName extends Data_Info  implements Serializable{
    //<editor-fold defaultstate="collapsed" desc="поля">

    @NaturalId
    @Column(length = 100, nullable = false, unique = true, updatable = true)
    private String name = "";
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "part_name", fetch = FetchType.EAGER)
    private List<VocabularyValue> vocabularyValues = new ArrayList<VocabularyValue>();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get-set">
    /**
     * Возвращает название раздела словаря
     *
     * @return название раздела словаря
     */
    public String getName() {
        return name;
    }

    /**
     * Назначает название раздела словаря
     *
     * @param name название раздела словаря
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает список всех значений из словаря по этому разделу
     * @return список всех значений из словаря по этому разделу
     */
    public List<VocabularyValue> getVocabularyValues() {
        return vocabularyValues;
    }

    /**
     * Назначает список всех значений из словаря по этому разделу
     * @param vocabularyValues список всех значений из словаря по этому разделу
     */
    public void setVocabularyValues(List<VocabularyValue> vocabularyValues) {
        this.vocabularyValues = vocabularyValues;
    }    
    //</editor-fold>

    /**
     * Переносит информацию из объекта параметра в данный. Всю, кроме id, даты
     * создания и СПИСКА ЗНАЧЕНИЙ
     *
     * @param obj объект-параметр
     */
    public void cloneForDB(PartName obj) {
        super.cloneForDB(obj);
        name = obj.name;
    }

    @Override
    public String toString() {
        return "PartName{" + "name=" + name + '}';
    }
}
