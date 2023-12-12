package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.Models.EntityClasses.User;

public class InvoiceParser extends Mail {
    public InvoiceParser(ReservationDTO reservationDTO, User user) {

        setTo(user.getEmail());
        setSubject("Reservation confirmation");
        setBody(

                "hi" + user.getFullName().getFirstName() + ",\n\n" +

                        "Thank you for choosing Reservista for your hotel reservations. We are delighted to confirm your reservation details. Please review the information below:\n\n" +

                        "Reservation Details:\n" +
                        "Reservation ID: " + reservationDTO.getReservationID() + "\n" +
                        "Check-in Date: " + reservationDTO.getCheckIn() + "\n" +
                        "Check-out Date: " + reservationDTO.getCheckOut() + "\n" +


                        reservationDTO.toString() +


                        "If you have any questions or need further assistance, feel free to reply to this email or contact our customer service at [Hotel Contact Number].\n" +


                        "Best Regards,\n\n" +

                        "Reservista"

        );
    }
}
