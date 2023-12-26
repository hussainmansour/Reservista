package Reservista.example.Backend.Services.Registration;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockedUserRepository blockedUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPService otpService;

    public User registerUser(RegistrationRequestDTO registrationRequest) throws GlobalException {


        //check users credentials
        checkUserCredentials(registrationRequest);

        //create user
        User user =
                User.builder()
                        .userName(registrationRequest.getUserName())
                        .fullName(FullName.builder()
                                .firstName(registrationRequest.getFirstName())
                                .lastName(registrationRequest.getLastName())
                                .build())
                        .email(registrationRequest.getEmail())
                        .password(passwordEncoder.encode(registrationRequest.getPassword()))
                        .birthDate(registrationRequest.getBirthDate())
                        .nationality(registrationRequest.getNationality())
                        .isActivated(false) //this attribute should be enabled after the user verifies his email using OTP
                        .build();


        try {
            //save user in the database
            User savedUser = userRepository.save(user);
            otpService.createAndSendOTP(savedUser);
            return savedUser;
        }
        catch (DataIntegrityViolationException e){
            throw new GlobalException(StatusCode.REGISTRATION_RACE_CONDITION,HttpStatus.CONFLICT);
        }

    }

    private void checkUserCredentials(RegistrationRequestDTO registrationRequest) throws GlobalException {

        if (blockedUserRepository.existsByEmail(registrationRequest.getEmail()))
            // locked, forbidden, unauthorized ??
            throw new GlobalException(StatusCode.ACCOUNT_BLOCKED, HttpStatus.FORBIDDEN); //403

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {


            if (!userRepository.findIsActivatedByEmail(registrationRequest.getEmail())) {
                otpService.refreshOTP(registrationRequest.getEmail());
                throw new GlobalException(StatusCode.ACCOUNT_DEACTIVATED, HttpStatus.CONFLICT);
            }
            throw new GlobalException(StatusCode.EMAIL_ALREADY_EXIST, HttpStatus.CONFLICT);
        }

        if (userRepository.existsByUserName(registrationRequest.getUserName()))
            throw new GlobalException(StatusCode.USERNAME_ALREADY_EXIST, HttpStatus.CONFLICT);

    }

}