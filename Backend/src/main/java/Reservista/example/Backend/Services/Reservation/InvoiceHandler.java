package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import org.springframework.stereotype.Service;

@Service
public class InvoiceHandler extends ReservationHandler {
    public ReservationResponseDTO handleRequest(ReservationRequestDTO reservationDTO) throws GlobalException {

        System.out.println("invoice reservation handler");
        StringBuilder s = new StringBuilder();
        s.append("Reservation Details:\n")
                .append("Reservation ID: ").append(reservationDTO.getReservationID()).append("\n")
                .append( "Check-in Date: ").append(reservationDTO.getCheckIn()).append("\n")
                .append("Check-out Date: ").append(reservationDTO.getCheckOut()).append("\n");

        s.append("Room Details:\n\n")
                .append(reservationDTO.getRoomTitle()).append("\n")
                .append("Room price: ").append(reservationDTO.getRoomPrice()).append("\n")
                .append("number of rooms reserved: ").append(reservationDTO.getReservedRooms().size()).append("\n");
        int i=1;
        for (ReservedRoomDTO r : reservationDTO.getReservedRooms()) {
            s.append("Room prices after optional services").append("\n");
            s.append("Room").append(i++).append(": ").append(r.getTotalPrice());
            if(r.isHasBreakfast())
                s.append("\t+Breakfast: ").append(reservationDTO.getHotelFoodOptions().getBreakfastPrice()).append("\n");
            if(r.isHasLunch())
                s.append("\t+Lunch: ").append(reservationDTO.getHotelFoodOptions().getLunchPrice()).append("\n");
            if(r.isHasLunch())
                s.append("\t+Dinner: ").append(reservationDTO.getHotelFoodOptions().getDinnerPrice()).append("\n");


        }
        if(reservationDTO.isRefundable()){
            s.append("Additional percentage due to refund option: +").append(reservationDTO.getRefundAdditionalPercentage()).append("%\n");
        }
        if(reservationDTO.getVoucherPercentage()!=0){
            s.append("Voucher discount : -").append(reservationDTO.getVoucherPercentage()).append("%\n");
        }
        s.append("Total price: ").append(reservationDTO.getFinalPrice());

        reservationDTO.setInvoice(s.toString());
        
        return nextHandler.handleRequest(reservationDTO);
    }

}
