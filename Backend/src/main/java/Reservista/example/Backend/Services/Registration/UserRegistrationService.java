package Reservista.example.Backend.Services.Registration;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public User registerUser(RegistrationRequestDTO registrationRequest) throws RegistrationCredentialsException, DeactivatedAccountException {


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
            throw new RegistrationCredentialsException("Email or username already exists");
        }

    }

    private void checkUserCredentials(RegistrationRequestDTO registrationRequest) throws RegistrationCredentialsException, DeactivatedAccountException {

        if (userRepository.findIsBlockedByEmail(registrationRequest.getEmail()))
            throw new RegistrationCredentialsException(StatusCode.ACCOUNT_BLOCKED.getMessage());

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {


            if (!userRepository.findIsActivatedByEmail(registrationRequest.getEmail())) {
                otpService.refreshOTP(registrationRequest.getEmail());
                throw new DeactivatedAccountException(StatusCode.ACCOUNT_DEACTIVATED.getMessage());
            }

            throw new RegistrationCredentialsException(StatusCode.EMAIL_ALREADY_EXIST.getMessage());
        }

        if (userRepository.existsByUserName(registrationRequest.getUserName()))
            throw new RegistrationCredentialsException(StatusCode.USERNAME_ALREADY_EXIST.getMessage());

    }

}