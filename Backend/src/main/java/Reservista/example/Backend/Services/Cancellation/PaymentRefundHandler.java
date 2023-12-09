package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentRefundHandler extends CancellationHandler{


    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO)  {

        Stripe.apiKey = stripeSecretApiKey;

        try {
            Refund.create(RefundCreateParams.builder()
                    .setPaymentIntent(cancellationRequestDTO.getPaymentIntentID())
                    .setAmount((long)cancellationRequestDTO.getRefundedAmount())
                    .build());

            return nextHandler.handleRequest(cancellationRequestDTO);

        } catch (StripeException e) {
            ResponseDTO<CancellationResponseDTO> cancellationResponseDTOResponseDTO
                    = new ResponseDTO<>(400, "Stripe refunding failed", null);
            return cancellationResponseDTOResponseDTO;
        }
    }
}
