package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class RefundCalculatorHandler extends CancellationHandler{
    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO)  {

        // This handler should calculate the amount to be redunded
        // taking in consideration the vouncher applied, the hotel refundable, etc.

        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
