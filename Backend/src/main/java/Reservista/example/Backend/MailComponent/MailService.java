package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

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
