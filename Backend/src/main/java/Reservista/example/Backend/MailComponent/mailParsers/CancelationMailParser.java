package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.MailComponent.Mail;

public class CancelationMailParser extends Mail {
    public void parseCancelationMAil(String to,String firstName){

        setTo(to);
        setSubject("Reservation Cancelation");
        setBody("Hi "+ firstName+"\n" +
                "We hope this message finds you well.\n" +
                "\n" +
                "This is to confirm that the reservation for [details of the reservation: hotel name, reservation ID, dates, etc.] has been successfully canceled per your request.\n" +
                "\n" +
                "Please note that any applicable refund or cancellation charges, if mentioned in the booking terms, will be processed according to the respective hotel's policy.\n" +
                "\n" +
                "If you have any further inquiries or require assistance with future reservations, feel free to reach out to our support team . We're here to help.\n" +
                "\n" +
                "Thank you for choosing Reservista. We look forward to assisting you with your future travel plans.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Reservista Team");

    }
}
