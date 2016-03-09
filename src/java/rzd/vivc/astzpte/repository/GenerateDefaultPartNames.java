/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import rzd.vivc.astzpte.repository.PartNameRepository;
import zdislava.common.vocabulary.PartName;
import zdislava.common.vocabulary.VocabularyValue;

/**
 *
 * @author VVolgina
 */
public class GenerateDefaultPartNames {
    public static void main(String[] args){
        PartName partName=new PartName();
        PartNameRepository prep=new PartNameRepository();
                partName.setName("Время на выполнение задания");
              partName.setId(1);
      prep.save(partName);
      
      partName.setName("Кол-во вопросов в билете");
              partName.setId(2);
      prep.save(partName);
        System.out.println(prep.getActivePartNames());
    }
}
