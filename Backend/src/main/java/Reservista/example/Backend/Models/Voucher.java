package Reservista.example.Backend.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_voucher",
            joinColumns = @JoinColumn(name = "voucher_code" , referencedColumnName = "voucher_code"),
            inverseJoinColumns = @JoinColumn(name = "username" , referencedColumnName = "username")
    )
    private List<User> users;
}
