package Reservista.example.Backend.DTOs.Registration;

import Reservista.example.Backend.Validators.Gmail;
import lombok.Data;

@Data
public class CodeVerificationDTO {
    @Gmail
    private String email;

    private String code;
}