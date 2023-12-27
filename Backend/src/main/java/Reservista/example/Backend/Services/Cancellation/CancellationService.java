package Reservista.example.Backend.Services.Cancellation;


import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancellationService {


    @Autowired
    PaymentRefundHandler paymentRefundHandler;

    @Autowired
    RefundAvailabilityCheckerHandler refundAvailabilityCheckerHandler;

    @Autowired
    RefundCalculatorHandler refundCalculatorHandler;

    @Autowired
    CancellationConfirmationHandler cancellationConfirmationHandler;

    @Autowired
    ReservationRepository reservationRepository;


    public long cancelReservation(String username, long reservationID) throws GlobalException {
        CancellationRequest cancellationRequest = CancellationRequest.builder().reservationID(reservationID).username(username).build();
        paymentRefundHandler.setNextHandler(cancellationConfirmationHandler);
        refundCalculatorHandler.setNextHandler(paymentRefundHandler);
        refundAvailabilityCheckerHandler.setNextHandler(refundCalculatorHandler);
        return refundAvailabilityCheckerHandler.handleRequest(cancellationRequest);
    }

    public long getRefundedAmount(String username, long reservationID) throws GlobalException {
        CancellationRequest cancellationRequest = CancellationRequest.builder().reservationID(reservationID).username(username).build();
        cancellationRequest.setUsername(username);
        refundCalculatorHandler.setNextHandler(null);
        refundAvailabilityCheckerHandler.setNextHandler(refundCalculatorHandler);
        return refundAvailabilityCheckerHandler.handleRequest(cancellationRequest);
    }
}
