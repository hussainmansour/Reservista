package Reservista.example.Backend.Services.Reservation;


import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentHandler extends ReservationHandler{

    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {

        try {
            Stripe.apiKey= stripeSecretApiKey;

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount((long) reservationDTO.getPrice())
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

            ResponseDTO<ReservationResponseDTO> responseDTO
                    = new ResponseDTO<>(StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getCode(),StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getMessage(),reservationResponseDTO);
            return responseDTO;
        }
        catch ( StripeException e){
            ResponseDTO<ReservationResponseDTO> responseDTO
                    = new ResponseDTO<>(StatusCode.STRIPE_PAYMENT_INTENT_FAILED.getCode(),StatusCode.STRIPE_PAYMENT_INTENT_FAILED.getMessage(),null);
            return responseDTO;
        }

    }
}
