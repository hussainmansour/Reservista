package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.OptionalServiceRepository;
import Reservista.example.Backend.DTOs.InvoiceComponent.OptionDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.InvoiceComponent.RoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class AdditionalOptionsHandler extends ReservationHandler{
    @Autowired
    OptionalServiceRepository optionalServiceRepository;
    @Override
    public  ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO){
        for(RoomDTO r : reservationDTO.getRooms()){
            if(r.getOptions()!=null && r.getOptions().size()!=0){
                for(OptionDTO o: r.getOptions()){
                    o.setPrice(optionalServiceRepository.findPriceOption(reservationDTO.getHotelID(),o.getName()));
                }
            }
        }
        return nextHandler.handleRequest(reservationDTO);

    }
}
