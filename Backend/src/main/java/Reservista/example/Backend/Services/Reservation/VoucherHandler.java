package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
public class VoucherHandler extends ReservationHandler {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional()
    public ReservationResponseDTO handleRequest(ReservationRequestDTO reservationDTO) throws GlobalException {
        //Here I have Reservation DTO that have UserID and VoucherName
        //Check if the voucher is not null
        //If (OK): firstly check the expiration date of the voucher
        //if ok continue else return status code with message "Voucher is expired"
        //If continued: check if the user used the voucher before or not
        //if used return status code with message "Voucher is already used"
        //else: add record in voucher user database table and add voucher price DTO then call the next handler

        //TODO: I need help here to how handle failure cases is by throwing exception or return status code directly


        if (reservationDTO.getVoucherCode() != null) {
            Voucher voucher = voucherRepository.findByVoucherCode(reservationDTO.getVoucherCode()).orElse(null);
            User user = userRepository.findByUserName(reservationDTO.getUserName()).orElse(null);
            handleVoucher(user, voucher);


            user.getVouchers().add(voucher);
            voucher.getUsers().add(user);
            reservationDTO.setVoucherPercentage(voucher.getDiscountRate());
            userRepository.save(user);
            voucherRepository.save(voucher);
            return nextHandler.handleRequest(reservationDTO);
        }
        return nextHandler.handleRequest(reservationDTO);
    }

    @Transactional
    public void handleVoucher(User user, Voucher voucher) throws GlobalException {
        if (voucher == null)
            throw new GlobalException(ErrorCode.VOUCHER_NOT_FOUND, HttpStatus.NOT_FOUND);

        Instant voucherExpirationDate = voucher.getExpiresAt();

        if (Instant.now().isAfter(voucherExpirationDate))
            throw new GlobalException(ErrorCode.EXPIRED_CODE, HttpStatus.GONE);


        Set<Voucher> usedVouchers = user.getVouchers();

        if (usedVouchers.contains(voucher))
            throw new GlobalException(ErrorCode.USED_VOUCHER, HttpStatus.CONFLICT);

    }

}