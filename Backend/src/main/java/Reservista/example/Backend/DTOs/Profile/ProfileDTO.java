package Reservista.example.Backend.DTOs.Profile;


import Reservista.example.Backend.Enums.Gender;
import Reservista.example.Backend.Validators.BirthDate;
import Reservista.example.Backend.Validators.Country;
import Reservista.example.Backend.Validators.Gmail;
import Reservista.example.Backend.Validators.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    @Username
    private String userName;

    @Gmail
    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    @BirthDate
    private LocalDate birthDate;


    private Gender gender;

    @Country( message = "Invalid nationality")
    private String nationality;

}
