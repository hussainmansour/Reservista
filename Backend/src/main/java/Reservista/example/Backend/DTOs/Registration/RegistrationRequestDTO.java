package Reservista.example.Backend.DTOs.Registration;

import Reservista.example.Backend.Enums.Genders;
import Reservista.example.Backend.Validators.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static Reservista.example.Backend.Config.ValidationUtil.invalidGender;


@Data
@Builder
public class RegistrationRequestDTO {

    @Gmail
    private String email;

    @StrongPassword
    private String password;

    @Username
    private String userName;

    @NotBlank
    private String firstName;

    private String lastName;

    private String middleName;

    @Age
    private LocalDate birthDate;

    @Nationality
    private String nationality;

//    @NotNull(message = invalidGender)
    @Gender
    private Genders gender;


}
