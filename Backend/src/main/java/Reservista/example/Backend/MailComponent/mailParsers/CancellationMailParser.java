package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.MailComponent.Mail;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CancellationMailParser extends Mail {

    public  CancellationMailParser(CancellationRequestDTO request) {


        String to = request.getEmail();
        String firstName=request.getFirstname();
        String hotelName = request.getHotelName();
        String checkOut = dateFormatter(request.getCheckOut());
        String checkIn = dateFormatter(request.getCheckIn());
        long reservationId = request.getReservationID();

        setTo(to);
        setSubject("Reservation Cancellation");
        setBody("Hi " + firstName + "\n" +
                "We hope this message finds you well.\n" +
                "\n" +
                "This is to confirm that the reservation for hotel " +hotelName+" with reservation ID "+reservationId+"\nfrom " +checkIn + " to " + checkOut + " has been successfully canceled per your request.\n" +
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

    private String dateFormatter (Instant instant){

        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

}
