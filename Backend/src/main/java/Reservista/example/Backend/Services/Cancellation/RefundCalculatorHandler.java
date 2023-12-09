package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.CancellationExceptionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class RefundCalculatorHandler extends CancellationHandler{
    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO) throws CancellationExceptionHandler {

        System.out.println("refund calculator handler");

        // gets the total price of the reservation and calculates the refunded amount
        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
