/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.hibernate.LazyInitializationException;
import rzd.vivc.astzpte.beans.session.AutorizationBean;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.model.ThemeWithQuestionCount;
import rzd.vivc.astzpte.repository.DepartmentRepository;
import rzd.vivc.astzpte.repository.PartNameRepository;
import rzd.vivc.astzpte.repository.ThemeRepository;
import rzd.vivc.astzpte.repository.TicketRepository;
import rzd.vivc.astzpte.repository.TicketTemplateRepository;
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
public class TicketsPageBean  implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="поля">
    private List<ThemeWithQuestionCount> list;
    //Минимальное к-во вопросов, необходимых для запуска теста по теме
    private long themeID=0;
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

    public long getThemeID() {
        return themeID;
    }


    public boolean isLink(int id){
        return list.get(id).getCountQuestion()>=0;
    }
    //</editor-fold>

    /**
     * Creates a new instance of AutorisationPageBean
     */
    public TicketsPageBean() {
        loadInfo();
    }

    //загрузка списка тем
    private void loadInfo() {
        
        try {
            list = (new TicketTemplateRepository()).getActiveTicketTemplatesCount(themeID);
        } catch (Exception e) {
            list = new ArrayList<ThemeWithQuestionCount>();
        }
    }

     @PostConstruct
    public void init() {
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("theme")) {
                themeID=new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("theme").toString());
            }
            loadInfo();
    }

   
}
