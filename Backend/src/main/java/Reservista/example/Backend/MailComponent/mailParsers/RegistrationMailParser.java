package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.Models.User;

public class RegistrationMailParser extends Mail {
    public RegistrationMailParser(User user) {
        setTo(user.getEmail());

        setSubject("Welcome to Reservista!");
        setBody("Hi " + user.getFirstName() + ",\n" +
                "\n" +
                "Thank you for registering with Reservista!\n" +
                "\n" +
                "You're now all set to discover and book hotels effortlessly through our app.\n" +
                "Enjoy seamless browsing and reservation services right at your fingertips.\n" +
                "\n" +
                "Should you need any assistance, don't hesitate to contact us.\n" +
                "\n" +
                "Happy booking!\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Reservista Team.");

    }
}
