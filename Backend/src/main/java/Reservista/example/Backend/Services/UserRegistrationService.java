package Reservista.example.Backend.Services;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Errors.CredentialsException;
import Reservista.example.Backend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public User registerUser(RegistrationRequestDTO registrationRequest) throws CredentialsException {


        //check users credentials
        checkUserCredentials(registrationRequest);

        //create user
        User user =
                User.builder()
                        .userName(registrationRequest.getUserName())
                        .firstName(registrationRequest.getFirstName())
                        .lastName(registrationRequest.getLastName())
                        .email(registrationRequest.getEmail())
                        .password(passwordEncoder.encode(registrationRequest.getPassword()))
                        .birthDate(registrationRequest.getBirthDate())
                        .nationality(registrationRequest.getNationality())
                        .isActivated(false) //this attribute should be enabled after the user verifies his email using OTP
                        .build();


        try {
            //save user in the database
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e){
            throw new CredentialsException("Email or username already exists");
        }

    }

    private void checkUserCredentials(RegistrationRequestDTO registrationRequest) throws CredentialsException {

        if (blockedUserRepository.existsByEmail(registrationRequest.getEmail()))
            throw new CredentialsException(StatusCode.ACCOUNT_BLOCKED.getMessage());

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {


            if (!userRepository.findIsActivatedByEmail(registrationRequest.getEmail())) {
                otpService.refreshOTP(registrationRequest.getEmail());
                throw new CredentialsException(StatusCode.ACCOUNT_DEACTIVATED.getMessage());
            }

            throw new CredentialsException(StatusCode.EMAIL_ALREADY_EXIST.getMessage());
        }

        if (userRepository.existsByUserName(registrationRequest.getUserName()))
            throw new CredentialsException(StatusCode.USERNAME_ALREADY_EXIST.getMessage());

    }

}
