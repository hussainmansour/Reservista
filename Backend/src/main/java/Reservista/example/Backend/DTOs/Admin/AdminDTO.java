package Reservista.example.Backend.DTOs.Admin;


import Reservista.example.Backend.Validators.StrongPassword;
import Reservista.example.Backend.Validators.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO {


    @Username
    private String adminName;

    @StrongPassword
    private String password;
}
