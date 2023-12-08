package Reservista.example.Backend.Services;

import static org.junit.jupiter.api.Assertions.*;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Services.Registration.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Test
    public void whenEmailAlreadyExists_thenUserAccountNotCreated(){


        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsActivatedByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        RegistrationCredentialsException exception = assertThrows(RegistrationCredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.EMAIL_ALREADY_EXIST.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any());

    }

    @Test
    public void whenUsernameAlreadyExists_thenUserAccountNotCreated(){

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(true);
        Mockito.when(userRepository.findIsActivatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        RegistrationCredentialsException exception = assertThrows(RegistrationCredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.USERNAME_ALREADY_EXIST.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }


    @Test
    public void whenUserIsBlocked_thenUserAccountNotCreated(){



        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsActivatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        RegistrationCredentialsException exception =assertThrows(RegistrationCredentialsException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.ACCOUNT_BLOCKED.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }


    @Test
    public void whenUserHasADeactivatedAccount_thenUserAccountNotCreatedAgain(){

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(true);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(true);
        Mockito.when(userRepository.findIsActivatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);

        RegistrationRequestDTO registrationRequest =
                RegistrationRequestDTO.builder()
                        .email("mariam@gmail.com")
                        .userName("mariam")
                        .build();

        DeactivatedAccountException exception =assertThrows(DeactivatedAccountException.class,()->userRegistrationService.registerUser(registrationRequest));

        assertEquals(StatusCode.ACCOUNT_DEACTIVATED.getMessage(), exception.getMessage());

        verify(userRepository,never()).save(any(User.class));

    }

    @Test
    public void whenValidUserCredentials_thenUserAccountCreated() throws RegistrationCredentialsException, DeactivatedAccountException {

        Mockito.when(userRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("mariam")).thenReturn(false);
        Mockito.when(userRepository.findIsActivatedByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(blockedUserRepository.existsByEmail("mariam@gmail.com")).thenReturn(false);
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setPassword(passwordEncoder.encode("password"));
            return savedUser;
        });

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

        assert result.getEmail().equals(registrationRequest.getEmail());

    }
}