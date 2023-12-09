package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CancellationConfirmationHandler extends CancellationHandler{

    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO) {
        System.out.println("cancellation confirmation handler");


        // handle database deletion
        // sends the mail
        return null;
    }
}
