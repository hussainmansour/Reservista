package Reservista.example.Backend.Controllers;


import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Models.User;
import Reservista.example.Backend.Services.Registration.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

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
                .build();

        when(userRegistrationService.registerUser(registrationRequest)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(StatusCode.SUCCESSFUL_REGISTRATION.getRespond())));
    }

    @Test
    public void whenRegistrationRequestDTOIsValidAndCredentialsException_thenReturn500() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        String mockMessage = "error";

        ResponseDTO expected = ResponseDTO.builder()
                .status(500)
                .message(mockMessage)
                .build();

        when(userRegistrationService.registerUser(registrationRequest)).thenThrow(new RegistrationCredentialsException(mockMessage));


        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenRegistrationRequestDTOIsValidAndDeactivatedAccountException_thenReturn200() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        when(userRegistrationService.registerUser(registrationRequest)).thenThrow(new DeactivatedAccountException("deactivated account"));


        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(StatusCode.ACCOUNT_DEACTIVATED.getRespond())));
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

        RegistrationResponseDTO responseDTO = RegistrationResponseDTO
                .builder()
                .email(StatusCode.INVALID_EMAIL.getMessage())
                .build();

        ResponseDTO expected = ResponseDTO.builder()
                .data(responseDTO)
                .status(400)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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

        RegistrationResponseDTO responseDTO = RegistrationResponseDTO
                .builder()
                .userName(StatusCode.INVALID_USERNAME.getMessage())
                .build();

        ResponseDTO expected = ResponseDTO.builder()
                .data(responseDTO)
                .status(400)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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

        RegistrationResponseDTO responseDTO = RegistrationResponseDTO
                .builder()
                .password(StatusCode.WEAK_PASSWORD.getMessage())
                .build();

        ResponseDTO expected = ResponseDTO.builder()
                .data(responseDTO)
                .status(400)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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

        RegistrationResponseDTO responseDTO = RegistrationResponseDTO
                .builder()
                .email(StatusCode.INVALID_EMAIL.getMessage())
                .userName(StatusCode.INVALID_USERNAME.getMessage())
                .password(StatusCode.WEAK_PASSWORD.getMessage())
                .nationality(StatusCode.INVALID_NATIONALITY.getMessage())
                .birthDate(StatusCode.INVALID_BIRTHDATE.getMessage())
                .build();

        ResponseDTO expected = ResponseDTO.builder().data(responseDTO).status(400).build();

        System.out.println(objectMapper.writeValueAsString(expected));
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }


}