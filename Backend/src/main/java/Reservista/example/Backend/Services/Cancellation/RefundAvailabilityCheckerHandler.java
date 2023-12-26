package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class RefundAvailabilityCheckerHandler extends CancellationHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public long handleRequest(CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        Reservation reservation = reservationRepository.findByIdAndUserUserName(cancellationRequestDTO.getReservationID(), cancellationRequestDTO.getUsername()).orElseThrow(()->new GlobalException(StatusCode.RESERVATION_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (!reservation.isConfirmed() )
            throw new GlobalException(StatusCode.RESERVATION_NOT_CONFIRMED, HttpStatus.CONFLICT);

        if (!reservation.getCheckIn().isAfter(Instant.now()))
            throw new GlobalException(StatusCode.RESERVATION_OUTDATED, HttpStatus.CONFLICT);

        cancellationRequestDTO.setFullyRefundable(reservation.isRefundable());
        cancellationRequestDTO.setTotalAmount(reservation.getPrice());
        cancellationRequestDTO.setPaymentIntentID(reservation.getPaymentIntentId());
        cancellationRequestDTO.setCheckIn(reservation.getCheckIn());
        cancellationRequestDTO.setCheckOut(reservation.getCheckOut());

        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
