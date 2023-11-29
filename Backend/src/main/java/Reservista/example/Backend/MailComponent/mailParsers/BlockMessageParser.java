package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.MailComponent.Mail;

public class BlockMessageParser extends Mail {
    public  BlockMessageParser(String email,String review,String firstNAme){
        setTo(email);
        setSubject("Account Deletion: Violation of Community Guidelines");
        setBody("Hi "+ firstNAme+"\n" +
                "\n" +
                "We regret to inform you that, despite previous warnings, your Reservista account has been permanently deleted due to continued violation of our community guidelines. Your account had received three prior warnings regarding inappropriate content.\n" +
                "\n" +
                "Here is your reported review:\n"+
                review+"\n"+
                "We value a respectful and welcoming environment for all our users. Unfortunately, repeated breaches of our guidelines have led to this irreversible action.\n" +
                "\n" +
                "We appreciate your past engagement with Reservista and regret any inconvenience caused. \n" +
                "\n" +
                "Thank you for your understanding.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Reservista Team");

    }
}
