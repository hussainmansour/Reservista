package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.CancellationExceptionHandler;
import Reservista.example.Backend.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class RefundAvailabilityCheckerHandler extends CancellationHandler{


    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public ResponseDTO<CancellationResponseDTO> handleRequest(CancellationRequestDTO cancellationRequestDTO) throws CancellationExceptionHandler {

        System.out.println("refund availability checker handler");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // check the reservation belong to the user and can be refunded
        Reservation reservation = reservationRepository.findReservationByIdAndUsernameAndCheckInFuture(
                        cancellationRequestDTO.getReservationID(), username)
                .orElseThrow(() -> new CancellationExceptionHandler("This reservation can't be cancelled"));

        // Setting attributes in the cancellationRequestDTO
        cancellationRequestDTO.setFullyRefundable(reservation.isFullyRefundable());
        cancellationRequestDTO.setRefundingPercentage(reservation.getRooms().get(0).getHotel().getFullyRefundableRate());
        cancellationRequestDTO.setPaymentIntentID(reservation.getPaymentIntentID());

        return nextHandler.handleRequest(cancellationRequestDTO);
    }
}
