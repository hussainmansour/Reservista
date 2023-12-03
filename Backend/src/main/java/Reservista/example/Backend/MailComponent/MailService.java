package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.DTOs.Respond;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    public Respond sendMail(Mail mail) {
        try {
            MailServiceProxy mailServiceProxy = new MailServiceProxy();
            return mailServiceProxy.sendMail(mail);
        } catch (Exception e) {
            return StatusCode.CREDENTIAL_ERROR.getRespond();
        }
    }
}
