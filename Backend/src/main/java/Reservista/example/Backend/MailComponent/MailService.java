package Reservista.example.Backend.MailComponent;

public class MailService {
    public int sendMail(Mail mail){
        try{
            MailServiceProxy mailService=new MailServiceProxy();
            return mailService.sendMail(mail);
        }
        catch (Exception e) {
            return 1;
        }
    }
}
