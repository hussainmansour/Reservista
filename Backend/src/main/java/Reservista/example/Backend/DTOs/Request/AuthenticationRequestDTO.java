package Reservista.example.Backend.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO{
    private String userNameOrEmail;
    private String password;
}