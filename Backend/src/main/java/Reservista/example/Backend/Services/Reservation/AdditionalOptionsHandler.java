package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DAOs.OptionalServiceRepository;
import Reservista.example.Backend.DTOs.InvoiceComponent.OptionDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.RoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

public class AdditionalOptionsHandler extends ReservationHandler{
    @Autowired
    OptionalServiceRepository optionalServiceRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public  ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO){
        for(RoomDTO r : reservationDTO.getRooms()){
            if(r.getOptions()!=null && r.getOptions().size()!=0){
                for(OptionDTO o: r.getOptions()){
                    o.setPrice(optionalServiceRepository.findOptionPrice(reservationDTO.getHotelID(),o.getName()));
                }
            }
        }
        if(reservationDTO.isRefundable()){
            if(hotelRepository.findHaveFullyRefundOptionById(reservationDTO.getHotelID())){
                reservationDTO.setAdditionalRefundPercentage(hotelRepository.findAdditionalRefundPercentageById(reservationDTO.getHotelID()));
            }
//            else return StatusCode.UNSUPPORTED_SERVICE.getRespond();

        }
        return nextHandler.handleRequest(reservationDTO);

    }
}
