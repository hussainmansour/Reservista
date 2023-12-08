package Reservista.example.Backend.Models.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    /*
    * reasons why string is unnecessary:
    *
    * (integer or long) has enough capacity (2 billion for INTEGER, 2^63 for long/BIGINT)
    * that it will suffice for 200+ years of use at the largest reasonably possible
    * transaction volumes.
    *
    * For example, at a current 10,000 records per day volume:
    * 3.65 million per year
    * times 20 for business growth = 73 million per year
    * times 200 years = 14.6 billion
    *
    * This is only ~34 bits -- too big for 'int', but using a long (63 bits positive)
    * it gives you spare capacity by a factor of 2^29 (~500 million) times.
    *
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @NotNull
    @Column(name = "check_in")
    private Instant checkIn;

    @NotNull
    @Column(name = "check_out")
    private Instant checkOut;

    @NotNull
    @Column(name = "voucher_applied")
    private boolean voucherApplied;

    // todo: payment data class ??

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "review_id",
            referencedColumnName = "id"
    )
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_name" , referencedColumnName = "user_name")
    private User user;

    @ManyToMany(mappedBy = "reservations", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Room> rooms;
}
