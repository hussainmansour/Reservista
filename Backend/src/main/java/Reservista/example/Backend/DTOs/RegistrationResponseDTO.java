package Reservista.example.Backend.DTOs;

import Reservista.example.Backend.Validators.Gmail;
import Reservista.example.Backend.Validators.StrongPassword;
import Reservista.example.Backend.Validators.Username;
import Reservista.example.Backend.Validators.ValidBirthDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegistrationResponseDTO {

    private String email;

    private String password;

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String response;

}
