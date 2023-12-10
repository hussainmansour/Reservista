package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
public class VoucherHandler extends ReservationHandler{

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {
        //Here I have Reservation DTO that have UserID and VoucherName
        //Check if the voucher is not null
        //If (OK): firstly check the expiration date of the voucher
        //if ok continue else return status code with message "Voucher is expired"
        //If continued: check if the user used the voucher before or not
        //if used return status code with message "Voucher is already used"
        //else: add record in voucher user database table and add voucher price DTO then call the next handler


        //TODO: I need help here to how handle failure cases is by throwing exception or return status code directly


        if(reservationDTO.getVoucherCode() == null){
            return nextHandler.handleRequest(reservationDTO);
        }else{
            Voucher voucher = voucherRepository.findVoucherByVoucherCode(reservationDTO.getVoucherCode());
            Instant voucherExpirationDate = voucher.getExpiresAt();

            if(Instant.now().isAfter(voucherExpirationDate)){
                //Throw exception "Voucher is expired"
            }else {
                User user = userRepository.findUserByUserName(reservationDTO.getUserID());
                Set<Voucher> usedVouchers = user.getVouchers();
                if(!usedVouchers.contains(voucher)){
                    usedVouchers.add(voucher);
                    reservationDTO.setVoucherPercentage(voucher.getDiscountRate());
                    userRepository.save(user);
                    nextHandler.handleRequest(reservationDTO);
                }else{
                    //Throw exception "Voucher is already used before"
                }
            }
        }

        return null;
    }
}