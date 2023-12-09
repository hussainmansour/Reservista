package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.CancellationExceptionHandler;
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
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO) throws CancellationExceptionHandler {

        System.out.println("payment Refund Handler");
        Stripe.apiKey = stripeSecretApiKey;

        try {
            Refund.create(RefundCreateParams.builder()
                    .setPaymentIntent(cancellationRequestDTO.getPaymentIntentID())
                            .setAmount((long)cancellationRequestDTO.getRefundedAmount())
                    .build());

            return nextHandler.handleRequest(cancellationRequestDTO);

        } catch (StripeException e) {
            throw new CancellationExceptionHandler("Stripe refunding failed");
        }
    }
}
