package Reservista.example.Backend.DTOs.Admin;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank
    private String voucherCode;

    @NotNull
    private int discountRate;

    @NotNull
    @Future
    private Instant expiresAt;

}
