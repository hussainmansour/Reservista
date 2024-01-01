package Reservista.example.Backend.DTOs.Registration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshOTPDTO {
    private String email;
}
