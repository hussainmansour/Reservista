package Reservista.example.Backend.DTOs;

import Reservista.example.Backend.Validators.Gmail;
import lombok.Data;

@Data
public class VerificationDTO {
    @Gmail
    private String email;

    private String code;
}