package Reservista.example.Backend.Controllers;


import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.RegistrationResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Exceptions.CredentialsException;
import Reservista.example.Backend.Models.User;
import Reservista.example.Backend.Services.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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
    public void whenRegistrationRequestDTOIsValidAndRegisterUserIsSuccessful_thenReturnOk() throws Exception {

        User user = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .response(StatusCode.SUCCESSFUL_REGISTRATION.getMessage())
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenRegistrationRequestDTOIsValidAndRegisterUserIsUnsuccessful_thenReturnBadRequest() throws Exception {

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        String mockMessage = "error";

        RegistrationResponseDTO expected = RegistrationResponseDTO.builder()
                .response(mockMessage)
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenThrow(new CredentialsException(mockMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }


    @Test
    public void whenEmailIsInValid_thenReturnBadRequest() throws Exception {

        User user = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail")
                .password("StrongPassword123!")
                .userName("testuser")
                .firstName("Test")
                .lastName("User")
                .build();

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .email(StatusCode.INVALID_EMAIL.getMessage())
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenUsernameIsInValid_thenReturnBadRequest() throws Exception {

        User userr = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .userName("test@user")
                .firstName("Test")
                .lastName("User")
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .userName(StatusCode.INVALID_USERNAME.getMessage())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenUsernameNotProvided_thenReturnBadRequest() throws Exception {

        User userr = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .password("StrongPassword123!")
                .firstName("Test")
                .lastName("User")
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .userName(StatusCode.INVALID_USERNAME.getMessage())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));

    }

    @Test
    public void whenWeakPassword_thenReturnBadRequest() throws Exception {

        User user = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail.com")
                .userName("hello")
                .password("weak")
                .firstName("Test")
                .lastName("User")
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .password(StatusCode.WEAK_PASSWORD.getMessage())
                .build();
        System.out.println("debuggg");
        System.out.println(objectMapper.writeValueAsString(expected));
        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void whenMultipleInvalidAttributes_thenReturnBadRequest() throws Exception {

        User user = User.builder().build();

        RegistrationRequestDTO registrationRequest = RegistrationRequestDTO.builder()
                .email("test@gmail")
                .password("weak")
                .lastName("User")
                .build();

        Mockito.when(userRegistrationService.registerUser(registrationRequest)).thenReturn("success");

        RegistrationResponseDTO expected = RegistrationResponseDTO
                .builder()
                .email(StatusCode.INVALID_EMAIL.getMessage())
                .userName(StatusCode.INVALID_USERNAME.getMessage())
                .password(StatusCode.WEAK_PASSWORD.getMessage())
                .build();

        System.out.println(objectMapper.writeValueAsString(expected));
        mockMvc.perform(MockMvcRequestBuilders.post("/register/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }


}