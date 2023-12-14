package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public abstract class ReservationHandler {
    protected ReservationHandler nextHandler;

    public abstract ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) ;
    public void setNextHandler(ReservationHandler reservationHandler){
        if(reservationHandler != null)
            this.nextHandler = reservationHandler;
    }
}
