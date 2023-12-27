package Reservista.example.Backend.DTOs.Reservation;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {

    private String clientSecret;
    private long reservationId;

}
