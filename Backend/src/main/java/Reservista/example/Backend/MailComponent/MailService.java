package Reservista.example.Backend.MailComponent;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MailService {


    public boolean sendMail(Mail mail) {
        try {
            MailServiceProxy mailServiceProxy = new MailServiceProxy();
            return mailServiceProxy.sendMail(mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }
    public boolean sendMailToAll(Mail mail, List<String> emails) {
        try {
            MailServiceProxy mailServiceProxy = new MailServiceProxy();
            for(String e: emails){
                mail.setTo(e);
                mailServiceProxy.sendMail(mail);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
