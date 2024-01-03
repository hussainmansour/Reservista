package Reservista.example.Backend.Controllers;


import Reservista.example.Backend.DTOs.ErrorDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Enums.Genders;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Services.Registration.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static Reservista.example.Backend.Config.ValidationUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @MockBean
    UserRegistrationService userRegistrationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void whenRegistrationRequestDTOIsValidAndRegisterUserIsSuccessful_thenReturn200() throws Exception {

        User user = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .gender(Genders.FEMALE)
                .build();

        when(userRegistrationService.registerUser(registrationRequest)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRegistrationRequestDTOIsValidAndCredentialsAreInvalid() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .gender(Genders.MALE)
                .build();

        ErrorDTO<String> expected = ErrorCode.TEST_CODE.getError();

        HttpStatus mockHttpsStatus = HttpStatus.BAD_REQUEST;

        when(userRegistrationService.registerUser(registrationRequest)).thenThrow(new GlobalException(ErrorCode.TEST_CODE, mockHttpsStatus));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenRegistrationRequestDTOIsValidAndDeactivatedAccountException_thenReturn409() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .gender(Genders.MALE)
                .build();

        ErrorDTO<String> expected = ErrorCode.ACCOUNT_DEACTIVATED.getError();

        when(userRegistrationService.registerUser(registrationRequest)).thenThrow(new GlobalException(ErrorCode.ACCOUNT_DEACTIVATED, HttpStatus.CONFLICT ));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }


    @Test
    public void whenEmailIsFormatIsInValid_thenReturn400() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("email",invalidEmail);

        ErrorDTO<Map<String, String>> expected = ErrorDTO.<Map<String, String>>builder()
                .errorCode(validationErrorCode)
                .data(fieldErrors)
                .build();


        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }


    @Test
    public void whenUsernameNotProvided_thenReturn400() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .firstName("Test")
                .lastName("User")
                .build();


        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("userName",invalidUsername);

        ErrorDTO<Map<String, String>> expected = ErrorDTO.<Map<String, String>>builder()
                .errorCode(validationErrorCode)
                .data(fieldErrors)
                .build();


        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));

    }

    @Test
    public void whenWeakPassword_thenReturn400() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .userName("hello")
                .password("weak")
                .firstName("Test")
                .lastName("User")
                .build();

        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("password",invalidPassword);

        ErrorDTO<Map<String, String>> expected = ErrorDTO.<Map<String, String>>builder()
                .errorCode(validationErrorCode)
                .data(fieldErrors)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenMultipleInvalidAttributes_thenReturn400() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail")
                .password("weak")
                .lastName("User")
                .nationality("Egyp")
                .birthDate(LocalDate.parse("2022-01-01"))
                .build();


        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("password",invalidPassword);
        fieldErrors.put("email",invalidEmail);
        fieldErrors.put("userName",invalidUsername);
        fieldErrors.put("nationality",invalidNationality);
        fieldErrors.put("birthDate",invalidAge);

        ErrorDTO<Map<String, String>> expected = ErrorDTO.<Map<String, String>>builder()
                .errorCode(validationErrorCode)
                .data(fieldErrors)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }
}