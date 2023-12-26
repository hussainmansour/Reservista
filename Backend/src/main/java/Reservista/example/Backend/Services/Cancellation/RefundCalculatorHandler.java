package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class RefundCalculatorHandler extends CancellationHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public long handleRequest(CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        int totalAmount = cancellationRequestDTO.getTotalAmount();
        long refundedAmount;

        if (cancellationRequestDTO.isFullyRefundable()){
            int fullyRefundableRate = reservationRepository.findFullRefundableRateByReservationId(cancellationRequestDTO.getReservationID());
            refundedAmount = (long) Math.ceil (totalAmount / (1 + fullyRefundableRate/100.0));
        }
        else {
            refundedAmount = (long) Math.ceil( 0.5 * totalAmount );
        }
        cancellationRequestDTO.setRefundedAmount(refundedAmount);

        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
