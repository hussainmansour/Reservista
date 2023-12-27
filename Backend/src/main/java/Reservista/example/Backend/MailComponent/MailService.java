package Reservista.example.Backend.MailComponent;


import org.springframework.stereotype.Service;


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
}
