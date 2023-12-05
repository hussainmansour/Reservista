package Reservista.example.Backend.DTOs.Registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationResponseDTO {

    private String email;

    private String password;

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String nationality;

    private String response;

}
