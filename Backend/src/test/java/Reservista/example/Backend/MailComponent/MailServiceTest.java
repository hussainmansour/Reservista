package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;

import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    @Test
    void testSendWithCorrectEmail()  {
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("elsamnimariam@gmail.com");
        mail.setBody("testing send with correct email");
        MailService mailservice=new MailService();
        assertEquals(mailservice.sendMail(mail), true);
    }
    @Test
    void testSendWithNoDomain(){
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("mariam.gerges1188");
        mail.setBody("testing send with no domain");
        MailService mailservice=new MailService();
        assertEquals(mailservice.sendMail(mail), false);
    }
    @Test
    void testSendWithEmptyEmail(){
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("");
        mail.setBody("testing send with empty email");
        MailService mailservice=new MailService();
        assertEquals(mailservice.sendMail(mail), false);
    }
}