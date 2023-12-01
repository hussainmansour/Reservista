package Reservista.example.Backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OTP {
    private static final int EXPIRATION_TIME = 5;
    @Id
    private String email;

    private String code;
    private Date expirationDate;

    public OTP(String email) {
        this.code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        this.email = email;
        this.expirationDate = calculateExpirationDate(EXPIRATION_TIME);

    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }

}
