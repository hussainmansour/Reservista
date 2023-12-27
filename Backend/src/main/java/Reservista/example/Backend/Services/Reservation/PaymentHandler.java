package Reservista.example.Backend.Services.Reservation;


import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentHandler extends ReservationHandler{

    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Override
    public ReservationResponseDTO handleRequest(ReservationDTO reservationDTO) throws GlobalException {

        try {
            Stripe.apiKey= "sk_test_51O5xO9IpHzJgrvA9mH85yoTzNH3je4DQNi7kk1oDAHbebXlpDt8E5JRB1iv84CyOOoW80zwNZow3NHi1xOXKxB9000xoFMSnpI";

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount((long) reservationDTO.getPrice()*100)
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);
            String paymentIntentID = intent.getId();
            
            reservationDTO.setPaymentIntentId(paymentIntentID);

            ReservationResponseDTO reservationResponseDTO
                    =ReservationResponseDTO
                    .builder()
                    .reservationId(reservationDTO.getReservationID())
                    .clientSecret(intent.getClientSecret())
                    .build();
            nextHandler.handleRequest(reservationDTO);
            return reservationResponseDTO;
        }
        catch (StripeException | GlobalException e){
            System.out.println(e.getMessage());
            throw new GlobalException(StatusCode.STRIPE_PAYMENT_INTENT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
