package Reservista.example.Backend.Models.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher")
public class Voucher {

    @Id
    @Column(name = "voucher_code")
    private String voucherCode;

    @Min(0)
    @Max(100)
    @Column(name = "discount_rate")
    private int discountRate;

    @NotNull
    @Column(name = "expires_at")
    private Instant expiresAt;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_voucher",
            joinColumns = @JoinColumn(name = "voucher_code", referencedColumnName = "voucher_code"),
            inverseJoinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    )
    private Set<User> users;
}
