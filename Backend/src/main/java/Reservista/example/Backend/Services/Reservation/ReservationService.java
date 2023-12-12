package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoucherHandler voucherHandler;

    public ResponseDTO<Integer> applyVoucher(String username, String voucherCode) {
        User user = userRepository.findByUserName(username).orElse(null);
        Voucher voucher = voucherRepository.findVoucherByVoucherCode(voucherCode);
        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);
        switch (statusCode) {
            case SUCCESS -> {
                return new ResponseDTO<>(statusCode.getCode(), statusCode.getMessage(), voucher.getDiscountRate());
            }
            default -> {
                return new ResponseDTO<>(statusCode.getCode(), statusCode.getMessage(), null);
            }
        }
    }

    public ResponseDTO<ReservationResponseDTO> reserve(String userName,ReservationDTO reservationDTO){
        reservationDTO.setUserName(userName);

        return null;
    }
}
