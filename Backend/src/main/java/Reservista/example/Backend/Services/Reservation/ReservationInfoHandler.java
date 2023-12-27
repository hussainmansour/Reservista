package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DAOs.RoomDescriptionRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationInfoHandler extends ReservationHandler {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomDescriptionRepository roomDescriptionRepository;

    @Override
    public ReservationResponseDTO handleRequest(ReservationRequestDTO reservationDTO) throws GlobalException {
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

    private void fillHotelName(ReservationRequestDTO reservationDTO, String hotelName) {
        reservationDTO.setHotelName(hotelName);
    }

    private void fillRoomPrice(ReservationRequestDTO reservationDTO, int roomPrice){
        reservationDTO.setRoomPrice(roomPrice);
    }

    private void fillHotelFoodOptions(ReservationRequestDTO reservationDTO, HotelFoodOptions hotelFoodOptions){
        reservationDTO.setHotelFoodOptions(hotelFoodOptions);
    }

    private void fillRefundAdditionalPercentage(ReservationRequestDTO reservationDTO, int hotelAdditionalRefundablePercentage){
        reservationDTO.setRefundAdditionalPercentage(hotelAdditionalRefundablePercentage);
    }
    private void fillRoomTitle(ReservationRequestDTO reservationDTO){
        reservationDTO.setRoomTitle(roomDescriptionRepository.findTitleById(reservationDTO.getRoomDescriptionId()));
    }
}
