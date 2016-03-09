/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import rzd.vivc.astzpte.common.constants.TZPTEConstants;
import rzd.vivc.astzpte.repository.PartNameRepository;
import rzd.vivc.astzpte.repository.VocabularyValueRepository;
import zdislava.common.vocabulary.PartName;
import zdislava.common.vocabulary.VocabularyValue;

/**
 *
 * @author VVolgina
 */
@ManagedBean
@ViewScoped
public class SettingsPageBean {
    
    private PartName timeN, countN;
    private VocabularyValue timeV, countV;
    private String time, count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        timeV.setText(time+"");
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
        countV.setText(count+"");
    }

    public void save(){
        VocabularyValueRepository rep=new VocabularyValueRepository();
        rep.save(timeV);
        rep.save(countV);
    }
    
    
    /**
     * Creates a new instance of SettingsPageBean
     */
    public SettingsPageBean() {
        PartNameRepository rep=new PartNameRepository();
        timeN=rep.get(TZPTEConstants.MAX_TIME);
        countN=rep.get(TZPTEConstants.COUNT_OF_QUESTIONS);
        timeV=timeN.getVocabularyValues().get(0);
        countV=countN.getVocabularyValues().get(0);
        time=timeV.getText();
        count=countV.getText();
    }
}
