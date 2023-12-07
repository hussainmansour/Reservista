package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

public class RoomAvailabilityHandler extends ReservationHandler{
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

}
