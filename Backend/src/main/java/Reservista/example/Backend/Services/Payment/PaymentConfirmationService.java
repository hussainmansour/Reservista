package Reservista.example.Backend.Services.Payment;

import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentConfirmationService {


    public void confirmPayment(Event event) throws EventDataObjectDeserializationException {

            PaymentIntent paymentIntent = ApiResource.GSON.fromJson(event.getDataObjectDeserializer().deserializeUnsafe().toJson(), PaymentIntent.class);
            String paymentIntentId = paymentIntent.getId();

            // TO DO
            // confirm the reservation using the paymentID in db
            // send confirmation mail
   
    }
}
