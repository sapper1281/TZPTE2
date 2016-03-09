/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import rzd.vivc.astzpte.dto.Department;

/**
 *
 * @author VVolgina
 */
public class DepartmentRepositoryTest {
    public static void main(String[] args){
        DepartmentRepository drep=new DepartmentRepository();
        Department dep=new Department();
        dep.setName("r1");
        drep.save(dep);
        System.out.println(dep);
        Department  dep1 = new Department();
         dep1.setName("f1");
        dep1.setId(4);
         DepartmentRepository drep1=new DepartmentRepository();
       
        drep1.save(dep1);
        System.out.println(dep);
    }
}
