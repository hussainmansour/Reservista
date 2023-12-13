package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

public class DatabaseReservationHandler extends ReservationHandler{
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {

        return nextHandler.handleRequest(reservationDTO);
    }

}
