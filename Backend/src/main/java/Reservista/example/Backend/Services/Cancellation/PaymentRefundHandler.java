package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

import Reservista.example.Backend.Error.GlobalException;
import org.springframework.stereotype.Service;


@Service
public class PaymentRefundHandler extends CancellationHandler{


    @Override
    public long handleRequest(CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        return nextHandler.handleRequest(cancellationRequestDTO);

    }
}
