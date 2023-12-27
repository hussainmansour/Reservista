package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
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

import static org.junit.jupiter.api.Assertions.*;

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
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        assertDoesNotThrow(() -> voucherHandler.handleVoucher(user, voucher));

    }

    @Test
    public void testNotFOUND(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();
        GlobalException exception = assertThrows(GlobalException.class,()->voucherHandler.handleVoucher(user, null));
        assertEquals(ErrorCode.VOUCHER_NOT_FOUND, exception.getErrorCode());
//        ErrorCode statusCode = voucherHandler.handleVoucher(user, null);
//
//        assertEquals(ErrorCode.VOUCHER_NOT_FOUND, statusCode);
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

        GlobalException exception = assertThrows(GlobalException.class,()->voucherHandler.handleVoucher(user, voucher));
        assertEquals(ErrorCode.EXPIRED_CODE, exception.getErrorCode());

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

        GlobalException exception = assertThrows(GlobalException.class,()->voucherHandler.handleVoucher(user, voucher));
        assertEquals(ErrorCode.USED_VOUCHER, exception.getErrorCode());

    }

}