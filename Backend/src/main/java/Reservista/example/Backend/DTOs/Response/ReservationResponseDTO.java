package Reservista.example.Backend.DTOs.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationResponseDTO {
    private String clientSecret;
    private long reservationId;
}
