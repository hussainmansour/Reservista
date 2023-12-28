package Reservista.example.Backend.Services.Cancellation;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancellationRequest {

    @NotNull(message = "please provide the reservation id")
    private Long reservationID;

    private String username;

    private String firstname;

    private long refundedAmount;

    private int refundingRate;

    private boolean isFullyRefundable;

    private String email;

    private String paymentIntentID;

    private int totalAmount;

    private Instant checkIn;

    private String hotelName;

    private  Instant checkOut;

}
