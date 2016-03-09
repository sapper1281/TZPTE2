/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import rzd.vivc.astzpte.dto.Answer;
import rzd.vivc.astzpte.dto.Department;
import rzd.vivc.astzpte.dto.Question;
import rzd.vivc.astzpte.dto.Theme;
import rzd.vivc.astzpte.repository.AnswerRepository;
import rzd.vivc.astzpte.repository.DepartmentRepository;
import rzd.vivc.astzpte.repository.QuestionRepository;
import rzd.vivc.astzpte.repository.ThemeRepository;

/**
 *
 * @author apopovkin
 */
@ManagedBean
@ViewScoped
public class QuestionPageBean implements Serializable {

    /*Все Вопросы при выборе темы*/
    private List<Question> questionAll;
    /*проверка выбрана ли тема, если выбрана появляется строка добаить запрос*/
    private boolean seeQuestion=false;
    /*id выбранной темы из списка*/
    private long themeId;
    /*Выбранная тема*/
     private Theme theme;
    /*Текст запроса*/
     private String nameQuestion = "";
     /*Список ответов для введенного запроса*/
     private List<Answer> answerAll  = new ArrayList<Answer>();
     public String edqqq1;

    public String getEdqqq1() {
        return edqqq1;
    }

    public void setEdqqq(String edqqq1) {
        String st="";
        this.edqqq1 = edqqq1+st;
    }

     

    
    
    /*галочка правельный ответ*/
    // private boolean value1;  

   /*вывод запроса*/ 
 //  private String nameAnswer = "";
    
    private Answer answer= new Answer();
    
   private Question question=new Question();
    
    
    
    
   
     
     
     
     
    /*!!!!!!!!!!не понятно для чего*/ 
    private String nameFile = "";
    //
    
    
    
    
    
     
   //<editor-fold defaultstate="collapsed" desc="get-set"> 
     public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
      public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
     
  /*  public String getNameAnswer() {
        return nameAnswer /*+ "" + nameFile*//*;
    }

    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }*/
    /* public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }*/
     
  
       public String getNameQuestion() {
        return nameQuestion ;
    }

    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }
       public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
    
    public List<Question> getQuestionAll() {
        return questionAll;
    }

    public void setQuestionAll(List<Question> questionAll) {
        this.questionAll = questionAll;
    }
     public boolean isSeeQuestion() {
        return seeQuestion;
    }

    public void setSeeQuestion(boolean seeQuestion) {
        this.seeQuestion = seeQuestion;
    }
     public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }
    //</editor-fold>
    
   
    
    
    
     /*редактирование вопроса*/
    public void findQuest(long f){
         answerAll= new ArrayList<Answer>();
         question=null;
         question=  (new QuestionRepository()).getQuestion(f);
        System.out.println("findQuest");
        List<Department>  departmentsSource1=new ArrayList<Department>();
        departmentsSource1.addAll((new QuestionRepository()).getDepartmentNotQuestions(question.getId()));
         List<Department> departmentsTarget1= new ArrayList<Department>();
         //departmentsTarget1.addAll((new QuestionRepository()).getDepartmentInQuestions(question.getId()));
       //  departmentsTarget1.addAll((new QuestionRepository()).getDepartmentNotQuestions(question.getId()));
        
    // List<Department>  departmentsTarget1 = question.getDepartments();
      // System.out.println("findQuest2 1 "+departmentsTarget);
    // List<Department>  departmentsSource1=(new QuestionRepository()).getDepartmentNotQuestions(question.getId());
      //System.out.println("findQuest2 2 "+departmentsSource);
     
        System.out.println("findQuest2");
    
     }   
    
    /*добавление вопроса*/
    public void newQuest(){
         answerAll= new ArrayList<Answer>();
         question=null;
         question=new Question();
         System.out.println("newQuest");

    
   }   
    
     
    
    /*добавление ответа */
    public void addAnswer() {
        if(answer.getId()==0){
        if(!question.getAnswers().contains(answer)){    
            
        question.getAnswers().add(answer);
        answerAll.add(answer);
        }
        
        }
        else{
       (
       /************************* question.getAnswers().get(answer.getId()).*/
               new AnswerRepository()).save(answer);
       question=  (new QuestionRepository()).get(question.getId());
       question.getAnswers().addAll(answerAll);
        }
      //  answer = new Answer();
        System.out.println("addAnswer");

    }
    /*создание нового ответа*/
    public void newAnswer() {
        answer = new Answer();
        System.out.println("newAnswer");
    }
    /*открыть отверт*/
     public void findAnswer(long f) {
         System.out.println("findAnswer "+f);
        if(f!=0){
        answer =  (new AnswerRepository()).get(f);}
        
        
        System.out.println("findAnswer");
    }
    
    
    /*сохранение вопроса*/
    public void  saveQuestion(){
        
     /*    System.out.println("------");

      for (int i = 0; i < question.getAnswers().size(); i++) {
	 question.getAnswers().get(i).setQuestion(question);
        }
        
        
        question.setFileName(nameFile);
        question.setTheme(theme);
        question.setText(edqqq1);
    
        System.out.println("====11============"+question.getFileName()+"="+question.getText());
    
        
    QuestionRepository quest=new QuestionRepository();
    
    quest.save(question);        
        
     questionAll=(new QuestionRepository()).getQuestionsForTheme((int)this.themeId);
     question.getAnswers().clear();
     question=null;
     nameFile="";
     answerAll=null;*/
     /*departmentsSource=(new DepartmentRepository()).getActiveDepartments();
     departmentsTarget= new ArrayList<Department>();
     departmenteds= new DualListModel<Department>(departmentsSource, departmentsTarget);*/
    
    }
    
   
    
    
    /*добавление файла*/
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        //this.nameFile = event.getFile().getFileName();
        
        
        
         nameFile=((new QuestionRepository()).getMax()+".jpg");
        
        
        try {
            File targetFolder = new File("D:\\file2\\");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    nameFile));
            question.setFileName(nameFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();

          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /*Удаление файла*/
    public void  delFile(){
question.setFileName("");
}
     /*Удаление вопроса*/
public void  delQuest(long f){
question=  (new QuestionRepository()).getQuestion(f);
question.setDeleted(true);
(new QuestionRepository()).save(question);
questionAll=(new QuestionRepository()).getQuestionsForTheme((int)this.themeId);
}   
    
   
public void onTransfer(TransferEvent event) {
        System.out.println("rr");
    }  


    
     /*При выборе темы появляется строка добавить вопрос и отображается список вопросов*/
     public void get_Theme() {
        if (themeId != 0) 
        {
            ThemeRepository themeRep = new ThemeRepository();
            theme = new Theme();
            theme = themeRep.get(this.themeId);
            System.out.println("111111111111 "+theme );
            seeQuestion=true;
            questionAll=(new QuestionRepository()).getQuestionsForTheme((int)this.themeId);
            question=null;
        } else
        {
            seeQuestion=false;
        }
        
            
    }
  
     
   
    
   
 
    
   
   
    

   

   
    
   

   
    
    

   
    
    
    
    
    
    
   
    
    

   
 
     
    
    
    
     
     
      
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
    
     
     
     
     
    
     

  
     
     
     
     
     
     
     
    



    /**
     * Creates a new instance of QuestionPageBean
     */
    
    public QuestionPageBean() {
        
    }

   
}
