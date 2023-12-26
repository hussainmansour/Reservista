package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.stereotype.Service;

@Service
public abstract class ReservationHandler {
    protected ReservationHandler nextHandler;

    public abstract ReservationResponseDTO handleRequest(ReservationDTO reservationDTO) throws GlobalException;
    public void setNextHandler(ReservationHandler reservationHandler){
        if(reservationHandler != null)
            this.nextHandler = reservationHandler;
    }
}
