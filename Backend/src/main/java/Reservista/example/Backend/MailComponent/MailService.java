package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.responds.Respond;

public class MailService {
    public Respond sendMail(Mail mail){
        try{
            MailServiceProxy mailService=new MailServiceProxy();
            return mailService.sendMail(mail);
        }
        catch (Exception e) {
            return StatusCode.CREDENTIAL_ERROR.getRespond();
        }
    }
}
