package Reservista.example.Backend.Services.Payment;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DAOs.TempReservationDetailsRepository;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.InvoiceParser;
import Reservista.example.Backend.MailComponent.mailParsers.RegistrationMailParser;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentConfirmationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    TempReservationDetailsRepository tempReservationDetailsRepository;


    public void confirmPayment(Event event) throws EventDataObjectDeserializationException {

        // TO DO

        // get reservation id by payment intent
        // modify is confirmed
        // from reservation id get the  invoice body
        // call mail service to send confirmation email

        PaymentIntent paymentIntent = ApiResource.GSON.fromJson(event.getDataObjectDeserializer().deserializeUnsafe().toJson(), PaymentIntent.class);
        String paymentIntentId = paymentIntent.getId();

        boolean isConfirmed = reservationRepository.setIsConfirmedToTrueByPaymentIntentId(paymentIntentId);
        if (!isConfirmed)  throw new NoSuchElementException("This reservation was not found");

        sendReservationConfirmationMail(paymentIntentId);
   
    }

    public void sendReservationConfirmationMail(String paymentIntentId){

        Object[] reservationDetails = reservationRepository.findEmailFirstNameReservationIdByPaymentIntentId(paymentIntentId).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

//        reservationDetailsList.get(0);
        String email = (String) reservationDetails[0];
        String firstName = (String) reservationDetails[1];
        Long reservationId = (Long) reservationDetails[2];

        String invoice = tempReservationDetailsRepository.findInvoiceByReservationId(reservationId).orElseThrow(() -> new NoSuchElementException("No invoice found"));

        Mail registrationMail = new InvoiceParser(email, firstName, invoice);

        mailService.sendMail(registrationMail);
    }


}
