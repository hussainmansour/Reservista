package Reservista.example.Backend.DTOs.Admin;


import Reservista.example.Backend.Validators.StrongPassword;
import Reservista.example.Backend.Validators.Username;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO {


    @NotBlank
    private String adminName;

    @NotBlank
    private String password;
}
