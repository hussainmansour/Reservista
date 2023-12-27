package Reservista.example.Backend.DTOs.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO{
    private String userNameOrEmail;
    private String password;
}