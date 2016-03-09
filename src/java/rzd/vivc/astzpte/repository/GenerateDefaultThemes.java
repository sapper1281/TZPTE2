/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import rzd.vivc.astzpte.dto.Theme;

/**
 *
 * @author vvolgina
 */
public class GenerateDefaultThemes {
    public static void main(String[] args){
        ThemeRepository themeRepository = new ThemeRepository();
        Theme theme1=new Theme();
        Theme theme2=new Theme();
        Theme theme3=new Theme();
        Theme theme4=new Theme();
        Theme theme5=new Theme();
        Theme theme6=new Theme();
        Theme theme7=new Theme();
        Theme theme8=new Theme();
        theme1.setId(1);
        theme2.setId(2);
        theme3.setId(3);
        theme4.setId(4);
        theme5.setId(5);
        theme6.setId(6);
        theme7.setId(7);
        theme8.setId(8);
        theme1.setName("Для работников, назначенных в качестве лиц, ответственных за обеспечение транспортной безопасности в субъекте транспортной инфраструктуры");
        theme2.setName("Для работников, назначенных в качестве лиц, ответственных за обеспечение транспортной безопасности на объекте транспортной инфраструктуры и (или) транспортном средстве");
        theme3.setName("Для работников субъекта транспортной инфраструктуры, подразделения транспортной безопасности, руководящих выполнением работ, непосредственно связанных с обеспечением транспортной безопасности объекта транспортной инфраструктуры и (или) транспортного средства");
        theme4.setName("Для работников, включенных в состав группы быстрого реагирования");
        theme5.setName("Для работников, осуществляющих досмотр, дополнительный досмотр, повторный досмотр в целях обеспечения транспортной безопасности");
        theme6.setName("Для работников, осуществляющих наблюдение и (или) собеседование в целях обеспечения транспортной безопасности");
        theme7.setName("Для работников, управляющих техническими средствами обеспечения транспортной безопасности");
        theme8.setName("Для иных работников субъекта транспортной инфраструктуры, подразделения транспортной безопасности, выполняющих работы, непосредственно связанные с обеспечением транспортной безопасности объекта транспортной инфраструктуры.");
        themeRepository.save(theme1);
        themeRepository.save(theme2);
        themeRepository.save(theme3);
        themeRepository.save(theme4);
        themeRepository.save(theme5);
        themeRepository.save(theme6);
        themeRepository.save(theme7);
        themeRepository.save(theme8);
    }
}
