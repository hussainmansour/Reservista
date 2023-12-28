package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.stereotype.Service;

@Service
public class CalculationHandler extends ReservationHandler {
    public ReservationResponseDTO handleRequest(ReservationRequestDTO reservationDTO) throws GlobalException {
        calculatePrice(reservationDTO);
        return nextHandler.handleRequest(reservationDTO);
    }

    public void calculatePrice(ReservationRequestDTO reservationDTO) {
        int total = 0;
        for (ReservedRoomDTO r : reservationDTO.getReservedRooms()) {
            int optionPrice = 0;
            if (r.isHasBreakfast()) {
                optionPrice += reservationDTO.getHotelFoodOptions().getBreakfastPrice();
            }
            if (r.isHasLunch()) {
                optionPrice += reservationDTO.getHotelFoodOptions().getLunchPrice();
            }
            if (r.isHasDinner()) {
                optionPrice += reservationDTO.getHotelFoodOptions().getDinnerPrice();
            }
            int roomAfterOptionalService = reservationDTO.getRoomPrice() + optionPrice;
            r.setTotalPrice(roomAfterOptionalService);
            total += roomAfterOptionalService;
        }
        reservationDTO.setPrice(total);
        total += total * (reservationDTO.getRefundAdditionalPercentage()/100);
        total -= total * (reservationDTO.getVoucherPercentage()/100);

        reservationDTO.setFinalPrice(total);
    }
}
