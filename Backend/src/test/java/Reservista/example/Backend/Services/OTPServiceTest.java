package Reservista.example.Backend.Services;

import Reservista.example.Backend.DAOs.OTPRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Models.EntityClasses.OTP;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Services.Registration.OTPService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
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
                .fullName(
                        FullName
                                .builder()
                                .firstName("mariam")
                                .lastName("gerges")
                                .build()
                )
                .email("mariam.gerges1188@gmail.com")
                .password("jKK&123jgj")
                .isActivated(false)
                .build();
    }

    User setUPUser2NotValidated() {
        return User.builder()
                .userName("mariamgerges")
                .fullName(
                        FullName
                                .builder()
                                .firstName("mariam")
                                .lastName("gerges")
                                .build()
                )
                .email("mariamgerges575@gmail.com")
                .password("jKK&123jgj")
                .isActivated(false)
                .build();
    }

    User setUPUser1Validated() {
        return User.builder()
                .userName("mariam")
                .fullName(
                        FullName
                                .builder()
                                .firstName("mariam")
                                .lastName("gerges")
                                .build()
                )
                .email("mariam.gerges1188@gmail.com")
                .password("jKK&123jgj")
                .isActivated(true)
                .build();
    }

    @BeforeEach
    void setUp() {
        LocalDateTime activeDate = getActiveDate();
        User user = setUPUser1NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email("mariam.gerges1188@gmail.com").expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);
    }

    LocalDateTime getExpiredDate() {
        return LocalDateTime.now().minusSeconds(1);
    }

    LocalDateTime getActiveDate() {
        return LocalDateTime.now().plusMinutes(5);
    }

    @Test
    void verifyOTPWithNotExistingOTPInDatabase() throws GlobalException {

        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyOTP("mariam.gerges118@gmail.com", "456789"));
        assertEquals(StatusCode.NOT_REGISTERED_USER, exception.getStatusCode());

    }

    @Test
    void verifyOTPWithWrongCode() {
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyOTP("mariam.gerges1188@gmail.com", "456789"));
        assertEquals(StatusCode.WRONG_VERIFICATION_CODE, exception.getStatusCode());
    }

    @Test
    void verifyOTPWithExpiredCode() throws GlobalException {
        LocalDateTime expiredDate = getExpiredDate();
        User user = setUPUser2NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt2 = OTP.builder().code("222222").email(user.getEmail()).expirationDate(expiredDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt2);
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyOTP(user.getEmail(), "222222"));
        assertEquals(StatusCode.EXPIRED_VERIFICATION_CODE, exception.getStatusCode());

    }

    @Test
    void verifyOTPWithExistingCorrectAndNotExpiredCode() throws GlobalException {
        assertEquals(otpService.verifyOTP("mariam.gerges1188@gmail.com", "111111"), true);
    }

    @Test
    void createAndSendOTPWithAlreadyExistingCodeForTheSameUser() {
        User user = setUPUser1NotValidated();
        assertEquals(otpService.createAndSendOTP(user), true);

    }

    @Test
    void createAndSendOTPWithNotExistingCodeForTheSameUser() {
        User user = setUPUser2NotValidated();
        assertEquals(otpService.createAndSendOTP(user), true);

    }

    @Test
    void refreshOTPRequestWithNonExistingUser() throws GlobalException {

        GlobalException exception = assertThrows(GlobalException.class,()->otpService.refreshOTP("mariamgerges575@gmail.com"));
        assertEquals(StatusCode.INVALID_OTP_REQUEST, exception.getStatusCode());

    }

    @Test
    void refreshOTPRequestWithAlreadyActivatedAccount() throws GlobalException {
        LocalDateTime activeDate = getActiveDate();
        User user = setUPUser1Validated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email(user.getEmail()).expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.refreshOTP("mariam.gerges1188@gmail.com"));
        assertEquals(StatusCode.INVALID_OTP_REQUEST, exception.getStatusCode());

    }

    @Test
    void refreshOTPRequestWithNotActivatedAccount() throws GlobalException {
        User user = setUPUser2NotValidated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> otpService.refreshOTP("mariamgerges575@gmail.com"));

    }

    @Test
    void verifyGmailAccountWithCorrectCode() throws GlobalException {
        assertDoesNotThrow(() -> otpService.verifyGmailAccount("mariam.gerges1188@gmail.com", "111111"));

    }

    @Test
    void verifyGmailAccountWithWrongCode() throws GlobalException {
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyGmailAccount("mariam.gerges1188@gmail.com", "245698"));
        assertEquals(StatusCode.WRONG_VERIFICATION_CODE, exception.getStatusCode());

    }

    @Test
    void verifyGmailAccountWithCorrectCodeButNotExistingUser() throws GlobalException {
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyGmailAccount("mariam@gmail.com", "111111"));
        assertEquals(StatusCode.NOT_REGISTERED_USER, exception.getStatusCode());
    }

    @Test
    void verifyGmailAccountWithCorrectCodeAndActivatedUser() throws GlobalException {
        LocalDateTime activeDate = getActiveDate();
        User user = setUPUser1Validated();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        OTP opt = OTP.builder().code("111111").email(user.getEmail()).expirationDate(activeDate).build();
        when(otpRepository.findByEmail(user.getEmail())).thenReturn(opt);
        GlobalException exception = assertThrows(GlobalException.class,()->otpService.verifyGmailAccount(user.getEmail(), "111111"));
        assertEquals(StatusCode.INVALID_OTP_REQUEST, exception.getStatusCode());

    }

}