package Reservista.example.Backend.Services;

import static org.junit.jupiter.api.Assertions.*;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Errors.CredentialsException;
import Reservista.example.Backend.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.ArgumentMatchers.any;
 import static org.mockito.Mockito.*;

@SpringBootTest
class UserRegistrationServiceTest {

    @Autowired
    UserRegistrationService userRegistrationService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    BlockedUserRepository blockedUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void whenEmailAlreadyExists_thenUserAccountNotCreated(){


        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsValidatedByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        CredentialsException exception = assertThrows(CredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.EMAIL_ALREADY_EXIST.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any());

    }

    @Test
    public void whenUsernameAlreadyExists_thenUserAccountNotCreated(){

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(true);
        Mockito.when(userRepository.findIsValidatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        CredentialsException exception = assertThrows(CredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.USERNAME_ALREADY_EXIST.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }


    @Test
    public void whenUserIsBlocked_thenUserAccountNotCreated(){



        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsValidatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        CredentialsException exception =assertThrows(CredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.ACCOUNT_BLOCKED.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }


    @Test
    public void whenUserHasAnUnactivatedAccount_thenUserAccountNotCreatedAgain(){

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(true);
        Mockito.when(userRepository.findIsValidatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        CredentialsException exception =assertThrows(CredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.ACCOUNT_DEACTIVATED.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }

    @Test
    public void whenValidUserCredentials_thenUserAccountCreated() throws CredentialsException {

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsValidatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .firstName("mariam")
                        .lastName("elsamni")
                        .userName("samni")
                        .password("password")
                        .build();

        User result = userRegistrationService.registerUser(registrationRequest);
        verify(userRepository, times(1)).save(any());

        assert result.getUsername().equals(registrationRequest.getUserName());
        assert result.getFirstName().equals(registrationRequest.getFirstName());
        assert result.getLastName().equals(registrationRequest.getLastName());
        assert result.getEmail().equals(registrationRequest.getEmail());
//        assert result.getPassword().equals(passwordEncoder.encode(registrationRequest.getPassword()));

    }


}