package Reservista.example.Backend.DTOs.Profile;


import Reservista.example.Backend.Enums.Genders;
import Reservista.example.Backend.Validators.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDTO {

    private String firstName;

    private String middleName;

    private String lastName;

    @BirthDate
    private LocalDate birthDate;

    @Gender
    private Genders gender;

    @Country( message = "Invalid nationality")
    private String nationality;
}
