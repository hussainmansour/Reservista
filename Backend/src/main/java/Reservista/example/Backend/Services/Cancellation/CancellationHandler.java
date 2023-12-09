package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;

public abstract class CancellationHandler {

    @Value("${stripe.secretKey}")
    private String stripeSecretApiKey;

    protected CancellationHandler nextHandler;

    public abstract ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO);

    public void setNextHandler(CancellationHandler reservationHandler){
        if(reservationHandler != null)
            this.nextHandler = reservationHandler;
    }

}

