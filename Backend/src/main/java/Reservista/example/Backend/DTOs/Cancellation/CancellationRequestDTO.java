package Reservista.example.Backend.DTOs.Cancellation;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancellationRequestDTO {

    @NotBlank
    private long reservationID;

    private String username;

    private int refundedAmount;

    private int refundingRate;

    private boolean isFullyRefundable;

    private String email;

    private String paymentIntentID;

}
