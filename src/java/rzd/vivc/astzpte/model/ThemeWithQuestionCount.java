/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.model;

import java.io.Serializable;

/**
 *Тема и к-во вопросов в ней
 * @author VVolgina
 */
public class ThemeWithQuestionCount implements Serializable{
    //<editor-fold defaultstate="collapsed" desc="поля">
    //id темы
    private long id;
    //название темы
    private String name;
    //к-во вопросов в ней
    private long countQuestion;
    private long num;

    //</editor-fold>
    
    public ThemeWithQuestionCount(long id, String name, long countQuestion) {
        this.id = id;
        this.name = name;
        this.countQuestion = countQuestion;
    }

    public ThemeWithQuestionCount(long id, long num, long countQuestion) {
        this.id = id;
        this.countQuestion = countQuestion;
        this.num = num;
    }

    public long getNum() {
        return num;
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    public void setNum(long num) {
        this.num = num;
    }

    /**
     *id темы
     * @return id темы
     */
    public long getId() {
        return id;
    }
    
    /**
     *id темы
     * @param id id темы
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * название темы
     * @return название темы
     */
    public String getName() {
        return name;
    }
    
    /**
     * название темы
     * @param name название темы
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * к-во вопросов в ней
     * @return к-во вопросов в ней
     */
    public long getCountQuestion() {
        return countQuestion;
    }
    
    /**
     * к-во вопросов в ней
     * @param countQuestion к-во вопросов в ней
     */
    public void setCountQuestion(long countQuestion) {
        this.countQuestion = countQuestion;
    }

    //</editor-fold>
    @Override
    public String toString() {
        return "ThemeWithQuestionCount{" + "id=" + id + ", name=" + name + ", countQuestion=" + countQuestion + '}';
    }



}
