package Reservista.example.Backend.Services.Payment;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DAOs.TempReservationDetailsRepository;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.InvoiceParser;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void confirmReservation(Event event) throws EventDataObjectDeserializationException {

        PaymentIntent paymentIntent = ApiResource.GSON.fromJson(event.getDataObjectDeserializer().deserializeUnsafe().toJson(), PaymentIntent.class);
        String paymentIntentId = paymentIntent.getId();
        reservationRepository.setIsConfirmedToTrueByPaymentIntentId(paymentIntentId);
   
    }

    public void sendReservationConfirmationMail(String paymentIntentId){

        List<Object[]> reservationDetails = reservationRepository.findEmailFirstNameReservationIdByPaymentIntentId(paymentIntentId).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

        Object[] reservationTempDetails = reservationDetails.get(0);
        String email = (String) reservationTempDetails[0];
        String firstName = (String) reservationTempDetails[1];
        Long reservationId = (Long) reservationTempDetails[2];

        String invoice = tempReservationDetailsRepository.findInvoiceByReservationId(reservationId).orElseThrow(() -> new NoSuchElementException("No invoice found"));

        Mail registrationMail = new InvoiceParser(email, firstName, invoice);

        mailService.sendMail(registrationMail);
    }

}
