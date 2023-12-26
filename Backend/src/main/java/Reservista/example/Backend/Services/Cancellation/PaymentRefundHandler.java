package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import org.springframeworhttps://github.com/hussainmansour/Reservista/pull/26/conflict?name=Backend%252Fsrc%252Fmain%252Fjava%252FReservista%252Fexample%252FBackend%252FServices%252FCancellation%252FPaymentRefundHandler.java&ancestor_oid=24b03fce01b1862519460bc960df3508f91b6b33&base_oid=434796449656bbbf06ec9bb46f51522016a35c64&head_oid=8140f1e55a6f1df18b3fff087732b19fa23afb4bk.beans.factory.annotation.Value;
import Reservista.example.Backend.Error.GlobalException;

import org.springframework.stereotype.Service;


@Service
public class PaymentRefundHandler extends CancellationHandler{


    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    @Override
    public long handleRequest(CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

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
