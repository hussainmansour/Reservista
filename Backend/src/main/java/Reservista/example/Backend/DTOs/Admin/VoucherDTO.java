package Reservista.example.Backend.DTOs.Admin;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDTO {

    @NotBlank
    private String voucherCode;

    @Min(1)
    @Max(100)
    private int discountRate;

    @NotNull
    @Future
    private Instant expiresAt;

}
