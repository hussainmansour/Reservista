package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.CancellationMailParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CancellationConfirmationHandler extends CancellationHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private MailService mailService;

    @Override
    public long handleRequest(CancellationRequest cancellationRequest) {

        List<Object[]> reservationDetails = reservationRepository.findEmailHotelNameFirstNameByReservationId(cancellationRequest.getReservationID()).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

        Object[] reservationTempDetails2 = reservationDetails.get(0);
        String email = (String) reservationTempDetails2[0];
        String hotelName = (String) reservationTempDetails2[1];
        String firstName = (String) reservationTempDetails2[2];

        cancellationRequest.setFirstname(firstName);
        cancellationRequest.setEmail(email);
        cancellationRequest.setHotelName(hotelName);

        Mail registrationMail = new CancellationMailParser(cancellationRequest);

        mailService.sendMail(registrationMail);

        reservationRepository.deleteById(cancellationRequest.getReservationID());

        return cancellationRequest.getRefundedAmount();

    }
}
