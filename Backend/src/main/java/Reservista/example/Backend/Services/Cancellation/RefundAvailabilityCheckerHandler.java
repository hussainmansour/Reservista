package Reservista.example.Backend.Services.Cancellation;
import Reservista.example.Backend.DAOs.ReservationRepository;
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
    public long handleRequest(CancellationRequest cancellationRequest) throws GlobalException {

        Reservation reservation = reservationRepository.findByIdAndUserUserName(cancellationRequest.getReservationID(), cancellationRequest.getUsername()).orElseThrow(()->new GlobalException(StatusCode.RESERVATION_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (!reservation.isConfirmed() )
            throw new GlobalException(StatusCode.RESERVATION_NOT_CONFIRMED, HttpStatus.CONFLICT);

        if (!reservation.getCheckIn().isAfter(Instant.now()))
            throw new GlobalException(StatusCode.RESERVATION_OUTDATED, HttpStatus.CONFLICT);

        cancellationRequest.setFullyRefundable(reservation.isRefundable());
        cancellationRequest.setTotalAmount(reservation.getPrice());
        cancellationRequest.setPaymentIntentID(reservation.getPaymentIntentId());
        cancellationRequest.setCheckIn(reservation.getCheckIn());
        cancellationRequest.setCheckOut(reservation.getCheckOut());

        return nextHandler.handleRequest(cancellationRequest);
    }
}
