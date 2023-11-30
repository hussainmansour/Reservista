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
        Random random = new Random();
        this.code = Integer.toString(random.nextInt(999999 - 100000 + 1) + 100000);
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
