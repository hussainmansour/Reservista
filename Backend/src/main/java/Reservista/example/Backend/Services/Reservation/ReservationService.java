package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoucherHandler voucherHandler;
    public ResponseDTO<Integer> applyVoucher(String username, String voucherCode){
        User user = userRepository.findUserByUserName(username);
        Voucher voucher = voucherRepository.findVoucherByVoucherCode(voucherCode);
        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);
        switch (statusCode){
            case SUCCESS -> {
                return new ResponseDTO<>(statusCode.getCode(), statusCode.getMessage(), voucher.getDiscountRate());
            }
            default -> {
                return new ResponseDTO<>(statusCode.getCode(), statusCode.getMessage(), null);
            }
        }
    }
}