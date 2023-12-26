package Reservista.example.Backend.DTOs.Response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {

    private String clientSecret;
    private long reservationId;

}
