/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.vivc.astzpte.dto.Answer;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.TicketTemplate;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import zdislava.common.dto.configuration.SessionFactorySingleton;

/**
 * Репозиторий для вопросов
 * @author VVolgina
 */
public class QuestionRepository {
    
     public List<Question> getQuestionsForTheme(int themeID) {
        List<Question> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = getQuestionsForTheme(themeID,sess);
        t.commit();
        sess.close();

        return dbList;
    }
     
     
     public Question get(long id){
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        Question get = (Question)sess.get(Question.class, id);
        t.commit();
        sess.close();

        return get;
    }
     
     public Question  getQuestion(long questID){
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
              
            Question  get =  (Question)sess.createQuery("select q from Question as q  where q.id=:questID and q.deleted=0").setLong("questID", questID).uniqueResult();

            
            
        t.commit();
        sess.close();

        return get;
    }
     
     
     public String getMax(){
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        String get =  sess.createSQLQuery("select max(q.id)+1 from Question as q" ).uniqueResult().toString();
        t.commit();
        sess.close();

        return get;
    }
     
   
   protected List<Question> getQuestionsForTheme(int themeID, Session sess){
       // return (List<Question>)sess.createQuery("from Question as q join q.theme as them on them.id=:depID").setLong("depID", themeID).list();
        return (List<Question>)sess.createQuery("select q from Question as q join q.ticketTemplate as them where them.id=:themID and them.deleted=0 and q.deleted=0").setInteger("themID", themeID).list();
    }
     
    public List<Department> getDepartmentNotQuestions(long questID) {
        List<Department> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = getDepartmentNotQuestions(questID,sess);
        t.commit();
        sess.close();

        return dbList;
    }
   
   protected List<Department> getDepartmentNotQuestions(long questID, Session sess){
       // return (List<Question>)sess.createQuery("from Question as q join q.theme as them on them.id=:depID").setLong("depID", themeID).list();
        return (List<Department>)sess.createQuery(
               // "select d from Department d, Question q join q.departments as dep where dep.deleted=0 and d.deleted=0 and q.deleted=0 and q.id=:questID and d.id<>dep.id").setLong("questID", questID).list();
         "select d from Department d, Question q where d.deleted=0 and q.deleted=0 and q.id=:questID and not (d in elements(q.departments)))").setLong("questID", questID).list();
                //"select d from Department as d where d.deleted=0 and NOT exist(select d1 from Question as q join q.department as d1 where q.id=:questID and d1.deleted=0 and q.deleted=0 and  d.id=d1.id) ").setInteger("questID", questID).list();
    }
   
   public List<Department> getDepartmentInQuestions(long questID) {
        List<Department> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = getDepartmentInQuestions(questID,sess);
        t.commit();
        sess.close();

        return dbList;
    }
   
   protected List<Department> getDepartmentInQuestions(long questID, Session sess){
       // return (List<Question>)sess.createQuery("from Question as q join q.theme as them on them.id=:depID").setLong("depID", themeID).list();
        return (List<Department>)sess.createQuery(
               // "select d from Department d, Question q join q.departments as dep where dep.deleted=0 and d.deleted=0 and q.deleted=0 and q.id=:questID and d.id<>dep.id").setLong("questID", questID).list();
         "select d from Department d, Question q where d.deleted=0 and q.deleted=0 and q.id=:questID and (d in elements(q.departments)))").setLong("questID", questID).list();
                //"select d from Department as d where d.deleted=0 and NOT exist(select d1 from Question as q join q.department as d1 where q.id=:questID and d1.deleted=0 and q.deleted=0 and  d.id=d1.id) ").setInteger("questID", questID).list();
    }
     
     
    
    protected List<Question> getQuestionsForDepartment(int departmentID, Session sess){
        return (List<Question>)sess.createQuery("from Question as q join q.departments as dep on dep.id=:depID").setLong("depID", departmentID).list();
    }
    
    protected UserAnswer getNextQuestion(long curentQuestionID, long ticketID, Session sess){
        return (UserAnswer)sess.createQuery("from UserAnswer as an where an.id>:cur and an.ticket.id=:tick").setLong("cur", curentQuestionID).setLong("tick", ticketID).setMaxResults(1).uniqueResult();
    }
    
    protected List<UserAnswer> getQuestionsForTicket(long ticketID, Session sess){
        return (ArrayList<UserAnswer>)sess.createQuery("from UserAnswer as an where an.ticket.id=:tick").setLong("tick", ticketID).setMaxResults(1).list();
    }
    
     public UserAnswer getNextQuestion(long curentQuestionID, long ticketID){
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

       UserAnswer bd=getNextQuestion(curentQuestionID, ticketID, sess);

        t.commit();
        sess.close();
        return bd;
    }
     
  protected void save(Question elem, Session sess) {
        sess.saveOrUpdate(elem);
    }

    public void save(Question elem) {
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();

        save(elem, sess);
        t.commit();
        sess.close();

    }
    
     public List<Question> getActiveQuestion() {
        List<Question> dbList;
        
        // извлекаем список неудаленных пользователей отсортированный по фамилии
        Session sess = SessionFactorySingleton.getSessionFactoryInstance()
                .openSession();
        Transaction t = sess.beginTransaction();
        // TODO подумать о критериях
        dbList = (List<Question>) sess.createQuery(
                "from Question where deleted=0 order by id").list();
        t.commit();
        sess.close();

        return dbList;
    }
     
     public static void main(String[] args) {
       /* Answer an=new Answer();
        an.setText("answ6");
        Department dep=new Department();
        dep.setId(1);
        Theme th=new Theme();
        th.setId(1);
        Question q=new Question();
        q.getAnswers().add(an);
        an.setQuestion(q);
        q.setTheme(th);
        q.getDepartments().add(dep);
        QuestionRepository rep=new QuestionRepository();
        rep.save(q);*/
         
        // List<Question> f=(new QuestionRepository()).getQuestionsForTheme(6);
         //System.out.println("========"+f);
           /* Iterator itr1 =(new QuestionRepository()).getQuestionsForTheme(6).iterator();
      while(itr1.hasNext()) {
         Object element = itr1.next();
         if( element instanceof Question ){
          System.out.println("--");
         }else{
         System.out.println("+++++ "+element );}
      }*/
          
                  QuestionRepository ticketRepository = new QuestionRepository();
        for (long i = 1; i <= 80; i++) {
            for (int j = 1; j <= 50; j++) {
                if (i != 1 && i != 11) {
                    Question ticket = new Question();
                    ticket.setNum(j);
                    ticket.setTicketTemplate(new TicketTemplate(i));
                    ticketRepository.save(ticket);
                }
            }
        }
    } 
}
