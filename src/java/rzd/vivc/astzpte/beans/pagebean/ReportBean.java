/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.vivc.astzpte.beans.pagebean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import rzd.vivc.astzpte.dto.Ticket;
import rzd.vivc.astzpte.dto.User;
import rzd.vivc.astzpte.dto.UserAnswer;
import rzd.vivc.astzpte.model.UserAnswerModel;
import rzd.vivc.astzpte.repository.UserRepository;

/**
 *
 * @author VVolgina
 */
public class ReportBean {

    public String generateReport(User usr) {
        HWPFDocument doc;
        Ticket ticket = usr.getTickets().get(0);
        List<UserAnswer> answers = usr.getTickets().get(0).getAnswers();
        ArrayList<UserAnswerModel> questions = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getAnswer() != null) {
                questions.add(new UserAnswerModel(answers.get(i), i));
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm");
        try (FileInputStream fis = new FileInputStream("c:\\rep\\templ.doc")) {
            doc = new HWPFDocument(fis);
            doc.getRange().getParagraph(3).replaceText("(dtBeg)", format.format(ticket.getDt_create()));
            doc.getRange().getParagraph(9).replaceText("(timeBeg)", format1.format(ticket.getDt_create()));
            doc.getRange().getParagraph(11).replaceText("(timeFin)", format1.format(ticket.getFinish()));
            long num = usr.getNum();
           /* for (int i = 1; i <= 13; i++) {
                long mod = num % 10;*/
                doc.getRange().replaceText("(num)"/* + (13 - i + 1) + ")"*/, num + "");
               /* num = num / 10;
            }*/

            doc.getRange().getParagraph(24).replaceText("(allow1)", usr.getAllowNum()+" от " + format.format(usr.getAllowDat()));

            doc.getRange().replaceText("(tickNum)", ticket.getAnswers().get(0).getQuestion().getTicketTemplate().getNum() + "");
            doc.getRange().replaceText("(themeNum)", ticket.getAnswers().get(0).getQuestion().getTicketTemplate().getTheme().getId() + "");
            doc.getRange().replaceText("(themeName)", ticket.getAnswers().get(0).getQuestion().getTicketTemplate().getTheme().getName());
            int count = 0;
            for (int i = 1; i <= 50; i++) {
                UserAnswerModel answerModel = questions.get(i - 1);
                if (i < 10) {
                    doc.getRange().replaceText("T0" + i, answerModel.getQuestion().getText());
                    doc.getRange().replaceText("C0" + i, answerModel.givenNumber() + "");
                    boolean cor = answerModel.correctNumber() == answerModel.givenNumber();
                    if (cor) {
                        count++;
                    }
                    doc.getRange().replaceText("Y0" + i, cor ? "Ответ правильный" : "Ответ не правильный");
                    doc.getRange().replaceText("B0" + i, cor ? 1 + "" : 0 + "");
                } else {
                    doc.getRange().replaceText("T" + i, answerModel.getQuestion().getText());
                    doc.getRange().replaceText("C" + i, answerModel.givenNumber() + "");
                    boolean cor = answerModel.correctNumber() == answerModel.givenNumber();
                    if (cor) {
                        count++;
                    }
                    doc.getRange().replaceText("Y" + i, cor ? "Ответ правильный" : "Ответ не правильный");
                    doc.getRange().replaceText("B" + i, cor ? 1 + "" : 0 + "");
                }
            }
            doc.getRange().replaceText("BT", count + "");
            doc.getRange().replaceText("BT", count + "");

            FileOutputStream fos = new FileOutputStream("c:\\rep\\"
                    + ticket.getId() + ".doc");
            doc.write(fos);
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ticket.getId() + ".doc";
    }

    public static void main(String[] args) {
        /*  long num = 1234567890123l;
         for (int i = 1; i <= 13; i++) {
         long mod = num % 10;
         System.out.println(mod+"");
         num =num/10;
         }*/
        User usr = (new UserRepository()).getWithTicket(53, 41);
        List<UserAnswer> answers = usr.getTickets().get(0).getAnswers();
        ArrayList<UserAnswerModel> questions = new ArrayList<UserAnswerModel>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getAnswer() != null) {
                questions.add(new UserAnswerModel(answers.get(i), i));
            }
        }

        Ticket tick = usr.getTickets().get(0);
        int max = 50;
        ReportBean reportBean = new ReportBean();
        String link = reportBean.generateReport(usr);
    }
}
