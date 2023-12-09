package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class RefundAvailabilityCheckerHandler extends CancellationHandler{

    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO)  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();

        // This handler should check whether the reservation could be refunded or not

        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
