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


    private final CancellationHandler cancellationChecker;
    private final CancellationHandler cancellationHandler;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    public CancellationService(CancellationConfirmationHandler cancellationConfirmationHandler, PaymentRefundHandler paymentRefundHandler, RefundCalculatorHandler refundCalculatorHandler, RefundAvailabilityCheckerHandler refundAvailabilityCheckerHandler ) {

        paymentRefundHandler.setNextHandler(cancellationConfirmationHandler);
        this.cancellationHandler = paymentRefundHandler;

        refundCalculatorHandler.setNextHandler(refundCalculatorHandler);
        this.cancellationChecker = refundAvailabilityCheckerHandler;

    }
    
    public long cancelReservation(String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {
        cancellationChecker.setNextHandler(cancellationHandler);
        cancellationRequestDTO.setUsername(username);
        return this.cancellationChecker.handleRequest(cancellationRequestDTO);
    }

    public long getRefundedAmount(String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {
        cancellationChecker.setNextHandler(null);
        cancellationRequestDTO.setUsername(username);
        return this.cancellationChecker.handleRequest(cancellationRequestDTO);
    }



}
