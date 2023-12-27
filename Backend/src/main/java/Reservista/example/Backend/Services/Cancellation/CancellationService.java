package Reservista.example.Backend.Services.Cancellation;


import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancellationService {


//    private final CancellationHandler cancellationChecker;
//    private final CancellationHandler cancellationHandler;
////    private final CancellationHandler paymentRefundHandler;

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

//    @Autowired
//    public CancellationService(CancellationConfirmationHandler cancellationConfirmationHandler, PaymentRefundHandler paymentRefundHandler, RefundCalculatorHandler refundCalculatorHandler, RefundAvailabilityCheckerHandler refundAvailabilityCheckerHandler ) {
//
//        paymentRefundHandler.setNextHandler(cancellationConfirmationHandler);
//        this.cancellationHandler = paymentRefundHandler;
//
//        refundAvailabilityCheckerHandler.setNextHandler(refundCalculatorHandler);
//        this.cancellationChecker = refundAvailabilityCheckerHandler;
//
//    }

    public long cancelReservation(String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        cancellationRequestDTO.setUsername(username);
        paymentRefundHandler.setNextHandler(cancellationConfirmationHandler);
        refundCalculatorHandler.setNextHandler(paymentRefundHandler);
        refundAvailabilityCheckerHandler.setNextHandler(refundCalculatorHandler);
        return refundAvailabilityCheckerHandler.handleRequest(cancellationRequestDTO);
//        cancellationChecker.setNextHandler(cancellationHandler);
//        cancellationRequestDTO.setUsername(username);
//        return this.cancellationChecker.handleRequest(cancellationRequestDTO);


    }

    public long getRefundedAmount(String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {
        cancellationRequestDTO.setUsername(username);
        refundCalculatorHandler.setNextHandler(null);
        refundAvailabilityCheckerHandler.setNextHandler(refundCalculatorHandler);
        return refundAvailabilityCheckerHandler.handleRequest(cancellationRequestDTO);
    }



}
