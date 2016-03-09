/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class WhyBean {

    private final VocabularyValue timeV;

    public VocabularyValue getTimeV() {
        return timeV;
    }

    public String getLors() {
        Pattern pattern = Pattern.compile("\\d+\\.\\s");
        Matcher matcher = pattern.matcher(timeV.getText());
        String res1 = matcher.replaceAll("</li><li>");
        return res1.substring(5, res1.length()-5);
    }

    /**
     * Creates a new instance of WhyBean
     */
    public WhyBean() {
        PartNameRepository rep = new PartNameRepository();
        PartName timeN = rep.get(TZPTEConstants.MAX_TIME);
        timeV = timeN.getVocabularyValues().get(0);
    }

}
