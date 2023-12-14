package Reservista.example.Backend.MailComponent.mailParsers;
import Reservista.example.Backend.MailComponent.Mail;

public class InvoiceParser extends Mail {
    public InvoiceParser(String email, String firstname, String invoice) {

        setTo(email);
        setSubject("Reservation confirmation");
        setBody(

                "hi " + firstname + ",\n\n" +

                        "Thank you for choosing Reservista for your hotel reservations.\n" +

                        " We are delighted to confirm your reservation details. Please review the information below:\n\n" +

                        "Reservation Details:\n" +

                        invoice +

                        "\n" +

                        "If you have any questions or need further assistance, feel free to reply to this email or contact our customer service at [Hotel Contact Number].\n\n" +


                        "Best Regards,\n\n" +

                        "Reservista"

        );
    }
}
