package Reservista.example.Backend.Models.EntityClasses;


import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "otp")
public class OTP {

    private static final int EXPIRATION_TIME_IN_MINs = 5;

    @Id
    @Gmail
    private String email;

    @NotBlank
    private String code;

    @NotNull
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    public OTP(String email) {
        this.code = String.
                valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        this.email = email;
        this.expirationDate = calculateExpirationDate();
    }

    private LocalDateTime calculateExpirationDate() {
        return LocalDateTime.now().plusMinutes(OTP.EXPIRATION_TIME_IN_MINs);
    }

}
