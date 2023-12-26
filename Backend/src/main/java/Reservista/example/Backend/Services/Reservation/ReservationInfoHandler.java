package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DAOs.RoomDescriptionRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationInfoHandler extends ReservationHandler {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomDescriptionRepository roomDescriptionRepository;

    @Override
    public ReservationResponseDTO handleRequest(ReservationDTO reservationDTO) throws GlobalException {
        Hotel hotel = hotelRepository.findById(reservationDTO.getHotelID()).orElse(null);
        int roomPrice = roomDescriptionRepository.findRoomDescriptionPrice(reservationDTO.getRoomDescriptionId());

        fillRoomPrice(reservationDTO, roomPrice);
        fillHotelName(reservationDTO, hotel.getName());
        fillHotelFoodOptions(reservationDTO, hotel.getHotelFoodOptions());
        fillRoomTitle(reservationDTO);
        if(reservationDTO.isRefundable())
            fillRefundAdditionalPercentage(reservationDTO, hotel.getFullyRefundableRate());

        return nextHandler.handleRequest(reservationDTO);

    }

    private void fillHotelName(ReservationDTO reservationDTO, String hotelName) {
        reservationDTO.setHotelName(hotelName);
    }

    private void fillRoomPrice(ReservationDTO reservationDTO, int roomPrice){
        reservationDTO.setRoomPrice(roomPrice);
    }

    private void fillHotelFoodOptions(ReservationDTO reservationDTO, HotelFoodOptions hotelFoodOptions){
        reservationDTO.setHotelFoodOptions(hotelFoodOptions);
    }

    private void fillRefundAdditionalPercentage(ReservationDTO reservationDTO, int hotelAdditionalRefundablePercentage){
        reservationDTO.setRefundAdditionalPercentage(hotelAdditionalRefundablePercentage);
    }
    private void fillRoomTitle(ReservationDTO reservationDTO){
        reservationDTO.setRoomTitle(roomDescriptionRepository.findTitleById(reservationDTO.getRoomDescriptionId()));
    }
}
