package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    public ResponseDTO<ReservationResponseDTO> reserve(String userName,ReservationDTO reservationDTO){
        reservationDTO.setUserName(userName);

        return null;
    }
}
