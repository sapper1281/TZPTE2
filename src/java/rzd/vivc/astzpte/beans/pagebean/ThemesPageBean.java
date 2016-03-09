/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import org.hibernate.LazyInitializationException;
import rzd.vivc.astzpte.beans.session.AutorizationBean;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.model.ThemeWithQuestionCount;
import rzd.vivc.astzpte.repository.DepartmentRepository;
import rzd.vivc.astzpte.repository.PartNameRepository;
import rzd.vivc.astzpte.repository.ThemeRepository;
import rzd.vivc.astzpte.repository.UserRepository;
import rzd.vivc.astzpte.repository.VocabularyValueRepository;
import zdislava.common.security.users.AdminRepository;
import zdislava.common.security.users.Administrator;
import zdislava.common.security.users.ExistingUserExeption;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class ThemesPageBean  implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="поля">
    private List<ThemeWithQuestionCount> list;
    //Минимальное к-во вопросов, необходимых для запуска теста по теме
    private long minCount=0;
        //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get-set">
       /**
     * Список тем для тестирования
     * @return Список тем для тестирования
     */
    public List<ThemeWithQuestionCount> getList() {
        return list;
    }

    /**
     *Список тем для тестирования
     * @param list Список тем для тестирования
     */
    public void setList(List<ThemeWithQuestionCount> list) {
        this.list = list;
    }

    /**
     * Минимальное к-во вопросов, необходимых для запуска теста по теме
     * @return Минимальное к-во вопросов, необходимых для запуска теста по теме
     */
    public long getMinCount() {
        return minCount;
    }
    public boolean isLink(int id){
        return list.get(id).getCountQuestion()>=0;
    }
    //</editor-fold>

    /**
     * Creates a new instance of AutorisationPageBean
     */
    public ThemesPageBean() {
        loadInfo();
    }

    //загрузка списка тем
    private void loadInfo() {
        
        try {
            list = new ThemeRepository().getActiveThemesCount();
        } catch (Exception e) {
            list = new ArrayList<ThemeWithQuestionCount>();
        }
    }

   
}
