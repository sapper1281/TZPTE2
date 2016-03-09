/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.dto.tickets;

import java.util.Random;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.repository.DepartmentRepository;

/**
 *
 * @author VVolgina
 */
public class TicketGeneratorTest {
    
    public void testRandom(){
        Random random = new Random();
        for(int i=0; i<25; i++){
            System.out.println(i+":"+random.nextInt(10));
        }
    }
    
    public static void main(String[] args){
         TicketGeneratorTest test= new TicketGeneratorTest();
         test.testRandom();
    }
}
