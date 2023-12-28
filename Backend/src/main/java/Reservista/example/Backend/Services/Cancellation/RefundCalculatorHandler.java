package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RefundCalculatorHandler extends CancellationHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public long handleRequest(CancellationRequest cancellationRequest) throws GlobalException {

        int totalAmount = cancellationRequest.getTotalAmount();
        long refundedAmount;

        if (cancellationRequest.isFullyRefundable()){
            int fullyRefundableRate = reservationRepository.findFullRefundableRateByReservationId(cancellationRequest.getReservationID());
            refundedAmount = (long) Math.ceil (totalAmount / (1 + fullyRefundableRate/100.0));
        }
        else {
            refundedAmount = (long) Math.ceil( 0.5 * totalAmount );
        }
        cancellationRequest.setRefundedAmount(refundedAmount);
        if (this.nextHandler == null) return refundedAmount;
        return nextHandler.handleRequest(cancellationRequest);
    }
}
