package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DTOs.InvoiceComponent.OptionDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.RoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;

public class CalculationHandler extends ReservationHandler{
    public  ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO){
        calculate_price(reservationDTO);
        return nextHandler.handleRequest(reservationDTO);
    }
    public void calculate_price(ReservationDTO reservationDTO) {
        double total = 0;
        for(RoomDTO r:reservationDTO.getRooms()){
            double optionPrice=0;
            if(r.getOptions()!=null ) {
                for (OptionDTO o : r.getOptions()) {
                    optionPrice+=o.getPrice();
                }
            }
            r.setOptionsTotalPrice(optionPrice);
            total+=optionPrice;
        }
        reservationDTO.setPrice(total);
        total+=total* reservationDTO.getRefundAdditionalPercentage();
        total-=total* reservationDTO.getVoucherPercentage();

        reservationDTO.setFinalPrice(total);
    }
}
