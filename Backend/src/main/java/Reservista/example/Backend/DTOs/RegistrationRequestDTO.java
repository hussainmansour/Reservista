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
public class RegistrationRequestDTO {

    @Gmail
    String email;

    @StrongPassword
    String password;

    @Username
    String userName;

//    @NotBlank(message = "please provide your first name")
    String firstName;

    String lastName;

    @ValidBirthDate
    LocalDate birthDate;

}
