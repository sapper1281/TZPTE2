/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import rzd.vivc.astzpte.beans.session.MenuIndex;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.repository.ThemeRepository;

/**
 *
 * @author apopovkin
 */
@ManagedBean
@ViewScoped
public class ThemePageBean implements Serializable  {

    private String nameTheme="" ;
    private String idStr = "";
    private boolean button_delete=false;
    private List<Theme> Theme;

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public boolean isButton_delete() {
        return button_delete;
    }

    public void setButton_delete(boolean button_delete) {
        this.button_delete = button_delete;
    }

    public List<Theme> getTheme() {
        return (new ThemeRepository()).getActiveThemes();
        
    }

    public void setTheme(List<Theme> Theme) {
        this.Theme = Theme;
    }
    
    
    
     public void delete_dep(){
         ThemeRepository themer=new ThemeRepository();
        Theme theme=new Theme();
        
        if(!idStr.equals("")){
        theme=themer.get(Long.parseLong(idStr));
        theme.setDeleted(true);
       }
        else
        {
        }
        themer.save(theme);
        all_null();
    }
    
    public void all_null(){
        this.nameTheme = "";
        this.idStr = "";
        this.button_delete=false;
    }
    
    public void save_dep(){
        
       ThemeRepository themer=new ThemeRepository();
        Theme theme=new Theme();
         
        if(!idStr.equals("")){
        theme=themer.get(Long.parseLong(idStr));
        theme.setName(this.nameTheme);
       }
        else
        theme.setName(this.nameTheme);
        
        themer.save(theme);
        all_null();
       }
    
    public void findAction(String f){
   
   setNameTheme(f );
   if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("id")) {
            this.idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id").toString();
            this.button_delete=true;
            System.err.println("button_delete--11-"+button_delete);
        } 
   } 

  
    
    
}
