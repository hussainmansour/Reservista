package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
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


        if(reservationDTO.getVoucherCode() != null){
            Voucher voucher = voucherRepository.findVoucherByVoucherCode(reservationDTO.getVoucherCode());
            User user = userRepository.findByUserName(reservationDTO.getUserName()).orElse(null);
            StatusCode statusCode = handleVoucher(user, voucher);

            switch (statusCode){
                case SUCCESS -> {
                    user.getVouchers().add(voucher);
                    voucher.getUsers().add(user);
                    reservationDTO.setVoucherPercentage(voucher.getDiscountRate());
                    userRepository.save(user);
                    voucherRepository.save(voucher);
                    return nextHandler.handleRequest(reservationDTO);
                }
                default -> {
                    ResponseDTO<ReservationResponseDTO> response = new ResponseDTO<>(statusCode.getCode(), statusCode.getMessage(), null);
                    return response;
                }
            }
        }
        return nextHandler.handleRequest(reservationDTO);
    }


    StatusCode handleVoucher (User user, Voucher voucher){
        if(voucher == null)
            return StatusCode.NOT_FOUND;

        Instant voucherExpirationDate = voucher.getExpiresAt();

        if(Instant.now().isAfter(voucherExpirationDate))
            return StatusCode.EXPIREDCODE;

        Set<Voucher> usedVouchers = user.getVouchers();

        if(usedVouchers.contains(voucher))
            return StatusCode.USEDVOUCHER;

        return StatusCode.SUCCESS;
    }
}