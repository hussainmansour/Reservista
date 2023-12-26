package Reservista.example.Backend.DTOs.Admin;


import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Validators.ExpiresAt;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

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

    @ExpiresAt
    private Instant expiresAt;

}
