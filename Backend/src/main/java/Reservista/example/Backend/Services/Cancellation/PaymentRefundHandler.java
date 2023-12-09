package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import org.springframework.stereotype.Service;


@Service
public class PaymentRefundHandler extends CancellationHandler{


    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO)  {

        return nextHandler.handleRequest(cancellationRequestDTO);

    }
}
