package Reservista.example.Backend.MailComponent;

import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.responds.Respond;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    @Test
    void testSendWithCorrectEmail()  {
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("mariam.gerges1188@gmail.com");
        mail.setBody("testing send with correct email");
        MailService mailservice=new MailService();
        Respond statusCode=mailservice.sendMail(mail);
        assertEquals(statusCode.getStatus(), StatusCode.SUCCESS.getCode());
    }
    @Test
    void testSendWithNoDomain(){
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("mariam.gerges1188");
        mail.setBody("testing send with no domain");
        MailService mailservice=new MailService();
        Respond statusCode=mailservice.sendMail(mail);
        assertEquals(statusCode.getStatus(), StatusCode.INVALID_ARGUMENT.getCode());
    }
    @Test
    void testSendWithEmptyEmail(){
        Mail mail=new Mail();
        mail.setSubject("TESTING");
        mail.setTo("");
        mail.setBody("testing send with empty email");
        MailService mailservice=new MailService();
        Respond statusCode=mailservice.sendMail(mail);
        assertEquals(statusCode.getStatus(), StatusCode.INVALID_ARGUMENT.getCode());
    }
}