package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;

public class PaymentHandler extends ReservationHandler{

    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) throws StripeException {

        // create payment intent (done)

        // save the paymentID in database

        // return responds in case of success and not success (done)

        try {
            Stripe.apiKey= stripeSecretApiKey;

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount((long) reservationDTO.getPrice())
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);
            ReservationResponseDTO reservationResponseDTO
                    =ReservationResponseDTO
                    .builder()
                    .reservationId(reservationDTO.getReservationID())
                    .paymentIntentId(intent.getClientSecret())
                    .build();

            ResponseDTO<ReservationResponseDTO> responseDTO
                    = new ResponseDTO<>(StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getCode(),StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getMessage(),reservationResponseDTO);
            return responseDTO;
        }
        catch ( StripeException e){
            ResponseDTO<ReservationResponseDTO> responseDTO
                    = new ResponseDTO<>(StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getCode(),StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getMessage(),null);
            return responseDTO;
        }

    }
}
