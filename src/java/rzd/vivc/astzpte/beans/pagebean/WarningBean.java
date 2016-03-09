/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.repository.PartNameRepository;
import zdislava.common.vocabulary.PartName;
import zdislava.common.vocabulary.VocabularyValue;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@RequestScoped
public class WarningBean {
    private final VocabularyValue timeV;

    public VocabularyValue getTimeV() {
        return timeV;
    }
    
    

    /**
     * Creates a new instance of WhyBean
     */
    public WarningBean() {
          PartNameRepository rep=new PartNameRepository();
        PartName timeN = rep.get(TZPTEConstants.COUNT_OF_QUESTIONS);
        timeV=timeN.getVocabularyValues().get(0);
    }
    
}
