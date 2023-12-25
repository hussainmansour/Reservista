package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    public ResponseDTO sendMail(Mail mail) {
        try {
            MailServiceProxy mailServiceProxy = new MailServiceProxy();
            return mailServiceProxy.sendMail(mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return StatusCode.CREDENTIAL_ERROR.getRespond();
        }
    }
}
