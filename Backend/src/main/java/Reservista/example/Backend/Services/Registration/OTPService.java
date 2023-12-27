package Reservista.example.Backend.Services.Registration;

import Reservista.example.Backend.DAOs.OTPRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.AccountActivationMailParser;
import Reservista.example.Backend.MailComponent.mailParsers.RegistrationMailParser;

import Reservista.example.Backend.Models.EntityClasses.OTP;
import Reservista.example.Backend.Models.EntityClasses.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class OTPService {

    @Autowired
    private MailService mailService;
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private UserRepository userRepository;

    public void verifyGmailAccount(String email, String code) throws GlobalException {

        verifyOTP(email, code);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || user.isActivated())
            throw new GlobalException(StatusCode.INVALID_OTP_REQUEST, HttpStatus.BAD_REQUEST);

        user.setActivated(true);
        userRepository.save(user);
        Mail registrationMail = new RegistrationMailParser(user);
        if (!mailService.sendMail(registrationMail)) throw new GlobalException(StatusCode.EMAIL_NOT_REACHED, HttpStatus.SERVICE_UNAVAILABLE); // 503

    }

    public void refreshOTP(String email) throws GlobalException {

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || user.isActivated())
            throw new GlobalException(StatusCode.INVALID_OTP_REQUEST, HttpStatus.BAD_REQUEST);


        OTP otp = otpRepository.findByEmail(email);
        if (otp != null)
            otpRepository.delete(otp);

        if (!createAndSendOTP(user))
            throw new GlobalException(StatusCode.EMAIL_NOT_REACHED, HttpStatus.SERVICE_UNAVAILABLE); // 503


    }

    public boolean createAndSendOTP(User user) {

        OTP newOtp = new OTP(user.getEmail());
        otpRepository.save(newOtp);
        Mail mail = new AccountActivationMailParser(newOtp.getCode(), user.getEmail(), user.getFullName().getFirstName());
        MailService mailService = new MailService();
        return mailService.sendMail(mail);

    }

    public boolean verifyOTP(String email, String code) throws GlobalException {
        OTP otp = otpRepository.findByEmail(email);
        if (otp == null) {
            throw new GlobalException(StatusCode.NOT_REGISTERED_USER, HttpStatus.NOT_FOUND );  ////// 404
        }

        if (!otp.getCode().equals(code)) {
            System.out.println("DB code: " + otp.getCode());
            System.out.println("sent code: " + code);
            throw new GlobalException(StatusCode.WRONG_VERIFICATION_CODE, HttpStatus.UNPROCESSABLE_ENTITY ); //422 validation failure
        }
        LocalDateTime now=LocalDateTime.now();
        if (now.compareTo(otp.getExpirationDate()) > 0) { //is the time now after expiration date
            otpRepository.delete(otp);

            throw new GlobalException(StatusCode.EXPIRED_VERIFICATION_CODE, HttpStatus.GONE ); //410
        }
        otpRepository.delete(otp);
        return true;

    }


}
