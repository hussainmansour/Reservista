package Reservista.example.Backend.Services.Registration;

import Reservista.example.Backend.DAOs.OTPRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.AccountActivationMailParser;
import Reservista.example.Backend.MailComponent.mailParsers.RegistrationMailParser;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reservista.example.Backend.Models.EntityClasses.OTP;

import java.time.LocalDateTime;
@Service
public class OTPService {

    @Autowired
    private MailService mailService;
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseDTO verifyGmailAccount(String email, String code) {

        ResponseDTO verifiedOTP = verifyOTP(email, code);
        if (verifiedOTP.getStatus() != StatusCode.SUCCESS.getCode()) {
            return verifiedOTP;
        }
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || user.isActivated()) {
            return StatusCode.INVALID_REQUEST.getRespond();
        }
        user.setActivated(true);
        userRepository.save(user);
        Mail registrationMail = new RegistrationMailParser(user);
        mailService.sendMail(registrationMail);
        return StatusCode.SUCCESS.getRespond();

    }

    public ResponseDTO refreshOTP(String email) {

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return StatusCode.INVALID_REQUEST.getRespond();
        }
        if (user.isActivated()) {
            return StatusCode.INVALID_REQUEST.getRespond();
        }
        OTP otp = otpRepository.findByEmail(email);
        if (otp != null) {
            otpRepository.delete(otp);
        }
        return createAndSendOTP(user);
    }

    public ResponseDTO createAndSendOTP(User user) {

        OTP newOtp = new OTP(user.getEmail());
        otpRepository.save(newOtp);
        Mail mail = new AccountActivationMailParser(newOtp.getCode(), user.getEmail(), user.getFullName().getFirstName());
        MailService mailService = new MailService();
        return mailService.sendMail(mail);

    }

    public ResponseDTO verifyOTP(String email, String code) {
        OTP otp = otpRepository.findByEmail(email);
        if (otp == null) {
            return StatusCode.NOT_REGISTERED_USER.getRespond();
        }

        if (!otp.getCode().equals(code)) {
            System.out.println("DB code: " + otp.getCode());
            System.out.println("sent code: " + code);
            return StatusCode.WRONG_VERIFICATION_CODE.getRespond();
        }
        LocalDateTime now=LocalDateTime.now();
        if (now.compareTo(otp.getExpirationDate()) > 0) { //is the time now after expiration date
            otpRepository.delete(otp);
            return StatusCode.EXPIRED_VERIFICATION_COD.getRespond();
        }
        otpRepository.delete(otp);
        return StatusCode.SUCCESS.getRespond();
    }


}
