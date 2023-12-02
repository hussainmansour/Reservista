package Reservista.example.Backend.DTOs;

import Reservista.example.Backend.Validators.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class RegistrationRequestDTO {

    @Gmail
    private String email;

    @StrongPassword
    private String password;

    @Username
    private String userName;

//    @NotBlank(message = "please provide your first name")
    private String firstName;

    private String lastName;

    @BirthDate
    private LocalDate birthDate;

    @Country
    private String nationality;

}
