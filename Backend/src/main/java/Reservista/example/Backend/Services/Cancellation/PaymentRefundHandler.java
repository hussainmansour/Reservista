package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.Enums.StatusCode;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import Reservista.example.Backend.Error.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class PaymentRefundHandler extends CancellationHandler{


    @Override
    public long handleRequest(CancellationRequest cancellationRequest) throws GlobalException {

        Stripe.apiKey = "sk_test_51O5xO9IpHzJgrvA9mH85yoTzNH3je4DQNi7kk1oDAHbebXlpDt8E5JRB1iv84CyOOoW80zwNZow3NHi1xOXKxB9000xoFMSnpI";

        try {
            Refund.create(RefundCreateParams.builder()
                    .setPaymentIntent(cancellationRequest.getPaymentIntentID())
                    .setAmount(cancellationRequest.getRefundedAmount()*100)
                    .build());

            return nextHandler.handleRequest(cancellationRequest);

        } catch (StripeException e) {
            System.out.println(e.getMessage());
            throw new GlobalException(StatusCode.STRIPE_CANCELLATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
