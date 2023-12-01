package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.MailComponent.Mail;

public class AccountActivationMailParser extends Mail {
    public AccountActivationMailParser(String code, String email, String firstName) {
        if (firstName == null) firstName = email;
        setTo(email);
        setSubject("Account activation");
        setBody("Hi " + firstName + "\n" +
                "\n" +
                "We received your request for a single-use code to activate with your Reservista account.\n" +
                "\n" +
                "Your single-use code is: " + code + "\n" +
                "\n" +
                "If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.\n" +
                "\n" +
                "Thanks,\n" +
                "Reservista Team");

    }
}
