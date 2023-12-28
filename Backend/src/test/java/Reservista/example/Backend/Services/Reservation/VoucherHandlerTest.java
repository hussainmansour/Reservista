package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class VoucherHandlerTest {

    @Autowired
    VoucherHandler voucherHandler;

    @MockBean
    VoucherRepository voucherRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testSuccess() throws GlobalException {
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4)))
                .users(new HashSet<>())
                .build();

        // Mock repository behavior
        when(voucherRepository.findByVoucherCode("WELCOME50")).thenReturn(Optional.ofNullable(voucher));
        when(userRepository.findByUserName("marwan123")).thenReturn(Optional.ofNullable(user));


        assertThrows(org.springframework.aop.AopInvocationException.class,
                () -> voucherHandler.handleRequest(createReservationRequestDTO(user.getUsername(), "WELCOME50")));

        // Verify that userRepository.save and voucherRepository.save are called once each
        verify(userRepository, times(1)).save(user);
        verify(voucherRepository, times(1)).save(voucher);
    }

    @Test
    public void testNotFound() {
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        GlobalException exception = assertThrows(GlobalException.class,
                () -> voucherHandler.handleRequest(createReservationRequestDTO(user.getUsername(), "INVALID_CODE")));
        assertEquals(ErrorCode.VOUCHER_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testExpiredCode() {
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("EXPIRED50")
                .discountRate(50)
                .expiresAt(Instant.now().minus(Duration.ofDays(4))).build();

        // Mock repository behavior
        when(voucherRepository.findByVoucherCode("EXPIRED50")).thenReturn(Optional.ofNullable(voucher));

        GlobalException exception = assertThrows(GlobalException.class,
                () -> voucherHandler.handleRequest(createReservationRequestDTO(user.getUsername(), "EXPIRED50")));
        assertEquals(ErrorCode.EXPIRED_CODE, exception.getErrorCode());
    }

    @Test
    public void testUsedVoucher() {
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("USED50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        user.getVouchers().add(voucher);

        // Mock repository behavior
        when(voucherRepository.findByVoucherCode("USED50")).thenReturn(Optional.ofNullable(voucher));
        when(userRepository.findByUserName("marwan123")).thenReturn(Optional.ofNullable(user));

        GlobalException exception = assertThrows(GlobalException.class,
                () -> voucherHandler.handleRequest(createReservationRequestDTO(user.getUsername(), "USED50")));
        assertEquals(ErrorCode.USED_VOUCHER, exception.getErrorCode());
    }

    private ReservationRequestDTO createReservationRequestDTO(String userName, String voucherCode) {
        ReservationRequestDTO reservationDTO = new ReservationRequestDTO();
        reservationDTO.setUserName(userName);
        reservationDTO.setVoucherCode(voucherCode);
        // Add other necessary fields to simulate a valid reservation request
        return reservationDTO;
    }
}
