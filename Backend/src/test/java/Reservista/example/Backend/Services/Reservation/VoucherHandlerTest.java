package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VoucherHandlerTest {

    @Autowired
    VoucherHandler voucherHandler;

    @MockBean
    VoucherRepository voucherRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testSuccess(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.SUCCESS, statusCode);
    }

    @Test
    public void testNotFOUND(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();
        StatusCode statusCode = voucherHandler.handleVoucher(user, null);

        assertEquals(StatusCode.NOT_FOUND, statusCode);
    }

    @Test
    public void testExpiredCode(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().minus(Duration.ofDays(4))).build();

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.EXPIREDCODE, statusCode);
    }

    @Test
    public void testUsedVoucher(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        user.getVouchers().add(voucher);

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.USEDVOUCHER, statusCode);
    }

}