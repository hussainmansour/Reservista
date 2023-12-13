package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Profile.*;
import Reservista.example.Backend.Enums.Genders;
import Reservista.example.Backend.Enums.SystemRoles;
import Reservista.example.Backend.Services.Profile.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
class ProfileControllerTest {

    @MockBean
    ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // For Authentication
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(

                        "husseinkhaled",// username
                        "password",// any password will work

                        List.of(new SimpleGrantedAuthority(SystemRoles.USER.name()))
                );
        securityContext.setAuthentication(authenticationToken);

        SecurityContextHolder.setContext(securityContext);
        // End of Authentication
    }


    @Test
    void viewExistingProfileReturnProfileFoundWithTheProfileDTO() throws Exception {
        String username = "husseinkhaled";
        ProfileDTO profileDTO = ProfileDTO.builder().userName("husseinkhaled")
                .firstName("hussein")
                .lastName("khadrawy")
                .middleName("khaled")
                .birthDate(LocalDate.parse("2002-09-09"))
                .email("husseinkhaled733@gmail.com")
                .nationality("Egypt")
                .gender(Genders.MALE)
                .build();
        System.out.println(profileDTO);

        when(profileService.viewProfile(username)).thenReturn(Optional.of(profileDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Profile found"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.userName").value("husseinkhaled"))
        .andExpect(jsonPath("$.data.firstName").value("hussein"))
        .andExpect(jsonPath("$.data.lastName").value("khadrawy"))
        .andExpect(jsonPath("$.data.middleName").value("khaled"))
        .andExpect(jsonPath("$.data.birthDate").value("2002-09-09"))
        .andExpect(jsonPath("$.data.email").value("husseinkhaled733@gmail.com"))
                .andExpect(jsonPath("$.data.gender").value(Genders.MALE.name()));
    }

    @Test
    void viewNonExistingProfileReturnProfileNotFound() throws Exception {
        String username = "mohamedkhaled";

        when(profileService.viewProfile(username)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Profile not found"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void updateExistingProfileReturnProfieupdated() throws Exception {
        String username = "husseinkhaled";
        UpdateDTO updateDTO = UpdateDTO.builder()
                .firstName("hussein")
                .lastName("khadrawy")
                .middleName("khaled")
                .birthDate(LocalDate.parse("2002-09-09"))
                .nationality("Egypt")
                .gender(Genders.MALE)
                .build();

        when(profileService.updateProfile(username, updateDTO)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/profile/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Profile updated"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void updateNonExistingProfileReturnProfilenotfound() throws Exception {
        String username = "Ibrahimkhaled";
        UpdateDTO updateDTO = UpdateDTO.builder()
                .firstName("hussein")
                .lastName("khadrawy")
                .middleName("khaled")
                .birthDate(LocalDate.parse("2002-09-09"))
                .nationality("Egypt")
                .gender(Genders.MALE)
                .build();

        when(profileService.updateProfile(username, updateDTO)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/profile/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Profile not found"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}
