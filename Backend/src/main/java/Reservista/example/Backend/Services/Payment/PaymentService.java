package Reservista.example.Backend.Services.Payment;


import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PaymentService {


    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Autowired
    Logger logger;

    public ResponseDTO cancelPayment(String reservationID){
        Stripe.apiKey = stripeSecretApiKey;
        // get payment intent from database
        String paymentIntentID ="";
        try {
            Refund.create(RefundCreateParams.builder()
                    .setPaymentIntent(paymentIntentID)
                    .build());

            // remove reservation from database

            return StatusCode.REFUND_SUCCESSFUL.getRespond();

        } catch (StripeException e) {
            logger.info("can't refund payment: "+e);
            return StatusCode.REFUND_UNSUCCESSFUL.getRespond();
        }
    }

    public ResponseDTO confirmPayment(String clientSecret){
        Stripe.apiKey = stripeSecretApiKey;
        String paymentIntentId = extractPaymentIntentId(clientSecret);

        if (paymentIntentId != null) {
            // Check the payment intent status
            try {
                PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
                System.out.println("Payment Intent Status: " + paymentIntent.getStatus());

            } catch (StripeException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid client secret format");
        }
        return StatusCode.SUCCESS.getRespond();
    }

    private static String extractPaymentIntentId(String clientSecret) {
        String[] parts = clientSecret.split("_secret_");
        if (parts.length == 2) {
            return parts[0];
        }
        return null;
    }
}
