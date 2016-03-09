/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.session;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author apopovkin
 */
@ManagedBean
@SessionScoped
public class MenuIndex implements Serializable {

    private Integer i;

    public Integer getI() {
           System.out.println(i);
        return i;
    }

    public void setI(Integer i) {
        
        this.i = i;
        System.out.println("--"+this.i);
    }
  
    
    
   
    public void iIndex(String i){
      setI(Integer.parseInt(i));
        System.out.println(this.i);
    
    }
    /**
     * Creates a new instance of MenuIndex
     */
    
}
