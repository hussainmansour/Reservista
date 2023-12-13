package Reservista.example.Backend.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ReservationResponseDTO {
    private String paymentIntentId;
    private String reservationId;
}
