package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;


import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoucherHandler voucherHandler;

    @Autowired
    ReservationRepository reservationRepository;

    private final ReservationHandler reservationHandler;

    @Autowired
    public ReservationService (CalculationHandler calculationHandler, DatabaseReservationHandler databaseReservationHandler, InvoiceHandler invoiceHandler, PaymentHandler paymentHandler,ReservationInfoHandler reservationInfoHandler, RoomAvailabilityHandler roomAvailabilityHandler, VoucherHandler voucherHandler){

        paymentHandler.setNextHandler(databaseReservationHandler);
        invoiceHandler.setNextHandler(paymentHandler);
        calculationHandler.setNextHandler(invoiceHandler);
        reservationInfoHandler.setNextHandler(calculationHandler);
        roomAvailabilityHandler.setNextHandler(reservationInfoHandler);
        voucherHandler.setNextHandler(roomAvailabilityHandler);
        this.reservationHandler = voucherHandler;

    }

    public int applyVoucher(String username, String voucherCode) throws GlobalException {
        User user = userRepository.findByUserName(username).orElse(null);
        Voucher voucher = voucherRepository.findByVoucherCode(voucherCode).orElse(null);
        voucherHandler.handleVoucher(user, voucher);

        return voucher.getDiscountRate();

    }

    public ReservationResponseDTO reserve(String userName, ReservationRequestDTO reservationDTO) throws GlobalException {

        reservationDTO.setUserName(userName);
        return this.reservationHandler.handleRequest(reservationDTO);

    }

    public void rollbackReservation(long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (!reservation.isConfirmed()) {
            reservationRepository.delete(reservation);
        }
    }
}
