package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class VoucherHandler extends ReservationHandler{

    @Autowired
    private VoucherRepository voucherRepository;
    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {
        //Here I have Reservation DTO that have UserID and VoucherName
        //Check if the voucher is not null
        //If (OK): firstly check the expiration date of the voucher
        //if ok continue else return status code with message "Voucher is expired"
        //If continued: check if the user used the voucher before or not
        //if used return status code with message "Voucher is already used"
        //else: add record in voucher user database table and add voucher price DTO then call the next handler

        if(reservationDTO.getVoucherCode() == null){
            return nextHandler.handleRequest(reservationDTO);
        }else{
            Instant voucherExpirationDate = voucherRepository
            .findVoucherByVoucherCode(reservationDTO.getVoucherCode())
            .getExpirationDate();

            if(Instant.now().isAfter(voucherExpirationDate)){
//                return new ResponseDTO<>()
            }





        }


        return null;
    }
}
