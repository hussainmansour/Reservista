
package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomAvailabilityHandler extends ReservationHandler{
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {

        //From DTO I want to get rooms type by type then reserve them one by one.
        //Loop over rooms and get their type
        //get all rooms from the database in the specified hotel (SELECT for UPDATE)
        //SELECT(SELECT FOR UPDATE * FROM ROOM WHERE HOTEL_ID = ID) WHERE ROOM_TYPE = 'title'
        //
        //
        //
        //nextHandler.handleRequest()
        return null;
    }
    public Reservation prepareReservation(ReservationDTO reservationDTO){
        Reservation reservation=Reservation.builder().user(userRepository.findByUserName(reservationDTO.getUserName()).orElse(null)).build();
        return null;
    }

}