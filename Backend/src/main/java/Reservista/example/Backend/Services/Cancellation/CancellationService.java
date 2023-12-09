package Reservista.example.Backend.Services.Cancellation;


import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CancellationService {


    private final CancellationHandler cancellationHandler;

    @Autowired
    public CancellationService(CancellationConfirmationHandler cancellationConfirmationHandler, PaymentRefundHandler paymentRefundHandler, RefundCalculatorHandler refundCalculatorHandler, RefundAvailabilityCheckerHandler refundAvailabilityCheckerHandler ) {

        paymentRefundHandler.setNextHandler(cancellationConfirmationHandler);
        refundCalculatorHandler.setNextHandler(paymentRefundHandler);
        this.cancellationHandler = refundAvailabilityCheckerHandler;
        this.cancellationHandler.setNextHandler(refundCalculatorHandler);

    }
    public ResponseDTO<CancellationResponseDTO> cancelReservation (CancellationRequestDTO cancellationRequestDTO) {

        return this.cancellationHandler.handleRequest(cancellationRequestDTO);

    }
}
