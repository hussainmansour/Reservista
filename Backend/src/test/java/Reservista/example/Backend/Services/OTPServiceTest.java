package Reservista.example.Backend.Services;

import Reservista.example.Backend.DAOs.OTPRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.OTP;
import Reservista.example.Backend.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OTPServiceTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OTPRepository otpRepository;
    @Autowired
    private OTPService otpService;


    User setUPUser1NotValidated() {
        return User.builder()
                .userName("mariam")
                .firstName("mariam")
                .lastName("gerges")
                .email("mariam.gerges1188@gmail.com")
                .password("jKK&123jgj")
                .isValidated(false)
                .build();
    }

    User setUPUser2NotValidated() {
        return User.builder()
                .userName("mariamgerges")
                .firstName("mariam")
                .lastName("gerges")
                .email("mariamgerges575@gmail.com")
                .password("jKK&123jgj")
                .isValidated(false)
                .build();
    }

    User setUPUser1Validated() {
        return User.builder()
                .userName("mariam")
                .firstName("mariam")
                .lastName("gerges")
                .email("mariam.gerges1188@gmail.com")
                .password("jKK&123jgj")
                .isValidated(true)
                .build();
    }

    @BeforeEach
    void setUp() {
        Date activeDate = getActiveDate();
        User user = setUPUser1NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email("mariam.gerges1188@gmail.com").expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);
    }

    Date getExpiredDate() {
        long currentTimeMillis = System.currentTimeMillis();
        return new Date(currentTimeMillis);
    }

    Date getActiveDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, 5);
        return new Date(calendar.getTime().getTime());
    }

    @Test
    void verifyOTPWithNotExistingOTPInDatabase() {
        assertEquals(otpService.verifyOTP("mariam@gmail.com", "456789").getStatus(), StatusCode.NOT_REGISTERED_USER.getCode());

    }

    @Test
    void verifyOTPWithWrongCode() {
        assertEquals(otpService.verifyOTP("mariam.gerges1188@gmail.com", "456789").getStatus(), StatusCode.WRONG_VERIFICATION_CODE.getCode());
    }

    @Test
    void verifyOTPWithExpiredCode() {
        Date expiredDate = getExpiredDate();
        User user = setUPUser2NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt2 = OTP.builder().code("222222").email(user.getEmail()).expirationDate(expiredDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt2);
        assertEquals(otpService.verifyOTP(user.getEmail(), "222222").getStatus(), StatusCode.EXPIRED_VERIFICATION_COD.getCode());

    }

    @Test
    void verifyOTPWithExistingCorrectAndNotExpiredCode() {
        assertEquals(otpService.verifyOTP("mariam.gerges1188@gmail.com", "111111").getStatus(), StatusCode.SUCCESS.getCode());
    }

    @Test
    void createAndSendOTPWithAlreadyExistingCodeForTheSameUser() {
        User user = setUPUser1NotValidated();
        assertEquals(otpService.createAndSendOTP(user).getStatus(), StatusCode.SUCCESS.getCode());

    }

    @Test
    void createAndSendOTPWithNotExistingCodeForTheSameUser() {
        User user = setUPUser2NotValidated();
        assertEquals(otpService.createAndSendOTP(user).getStatus(), StatusCode.SUCCESS.getCode());

    }

    @Test
    void refreshOTPRequestWithNonExistingUser() {
        assertEquals(otpService.refreshOTP("mariamgerges575@gmail.com").getStatus(), StatusCode.INVALID_REQUEST.getCode());

    }

    @Test
    void refreshOTPRequestWithAlreadyActivatedAccount() {
        Date activeDate = getActiveDate();
        User user = setUPUser1Validated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email(user.getEmail()).expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);

        assertEquals(otpService.refreshOTP("mariam.gerges1188@gmail.com").getStatus(), StatusCode.INVALID_REQUEST.getCode());

    }

    @Test
    void refreshOTPRequestWithNotActivatedAccount() {
        User user = setUPUser2NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(otpService.refreshOTP("mariamgerges575@gmail.com").getStatus(), StatusCode.SUCCESS.getCode());

    }

    @Test
    void verifyGmailAccountWithCorrectCode() {
        assertEquals(otpService.verifyGmailAccount("mariam.gerges1188@gmail.com", "111111").getStatus(), StatusCode.SUCCESS.getCode());

    }

    @Test
    void verifyGmailAccountWithWrongCode() {
        assertEquals(otpService.verifyGmailAccount("mariam.gerges1188@gmail.com", "245698").getStatus(), StatusCode.WRONG_VERIFICATION_CODE.getCode());

    }

    @Test
    void verifyGmailAccountWithCorrectCodeButNotExistingUser() {
        assertEquals(otpService.verifyGmailAccount("mariam@gmail.com", "111111").getStatus(), StatusCode.NOT_REGISTERED_USER.getCode());

    }

    @Test
    void verifyGmailAccountWithCorrectCodeAndActivatedUser() {
        Date activeDate = getActiveDate();
        User user = setUPUser1Validated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email(user.getEmail()).expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);
        assertEquals(otpService.verifyGmailAccount(user.getEmail(), "111111").getStatus(), StatusCode.INVALID_REQUEST.getCode());

    }

}