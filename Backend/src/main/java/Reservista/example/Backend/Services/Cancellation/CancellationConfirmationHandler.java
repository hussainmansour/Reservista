package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.CancellationMailParser;
import Reservista.example.Backend.MailComponent.mailParsers.InvoiceParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public long handleRequest(CancellationRequestDTO cancellationRequestDTO) {

        reservationRepository.deleteById(cancellationRequestDTO.getReservationID());

        List<Object[]> reservationDetails = reservationRepository.findEmailHotelNameFirstNameByReservationId(cancellationRequestDTO.getReservationID()).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

        Object[] reservationTempDetails2 = reservationDetails.get(0);
        String email = (String) reservationTempDetails2[0];
        String hotelName = (String) reservationTempDetails2[1];
        String firstName = (String) reservationTempDetails2[2];

        cancellationRequestDTO.setFirstname(firstName);
        cancellationRequestDTO.setEmail(email);
        cancellationRequestDTO.setHotelName(hotelName);

        Mail registrationMail = new CancellationMailParser(cancellationRequestDTO);

        mailService.sendMail(registrationMail);

        return cancellationRequestDTO.getRefundedAmount();
    }
}
