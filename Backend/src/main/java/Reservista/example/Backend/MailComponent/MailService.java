package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;

public class MailService {
    public int sendMail(Mail mail){
        try{
            MailServiceProxy mailService=new MailServiceProxy();
            return mailService.sendMail(mail);
        }
        catch (Exception e) {
            return StatusCode.CREDENTIAL_ERROR.getCode();
        }
    }
}
