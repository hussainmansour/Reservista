package Reservista.example.Backend.Models.EntityClasses;


import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "otp")
public class OTP {

    private static final int EXPIRATION_TIME_IN_MINs = 5;

    @Id
    @Gmail
    private String email;

    private String code;
    private LocalDateTime expirationDate;

    public OTP(String email) {
        this.code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        this.email = email;
        this.expirationDate = calculateExpirationDate();

    }

    private LocalDateTime calculateExpirationDate() {
        return LocalDateTime.now().plusMinutes(OTP.EXPIRATION_TIME_IN_MINs);
    }

}
