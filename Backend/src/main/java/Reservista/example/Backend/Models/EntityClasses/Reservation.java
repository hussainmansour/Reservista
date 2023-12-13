package Reservista.example.Backend.Models.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
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


    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "reservation_date")
    @CreationTimestamp
    private Instant reservationDate;

    @NotNull
    @Column(name = "check_in")
    private Instant checkIn;

    @NotNull
    @Column(name = "check_out")
    private Instant checkOut;

    @Column(name = "voucher_applied")
    private boolean voucherApplied = false;

    @NotBlank
    @Column(name = "payment_intent_id" , unique = true)
    private String paymentIntentId;

    @Column(name = "is_confirmed")
    private boolean isConfirmed = false;

    @Column(name = "is_refundable")
    private boolean isRefundable = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_description_id" , referencedColumnName = "id" , nullable = false)
    private RoomDescription roomDescription;

    @OneToOne(mappedBy = "reservation" , cascade = CascadeType.ALL)
    private TempReservationDetails tempReservationDetails;

    @OneToMany(mappedBy = "reservation" , cascade = CascadeType.ALL)
    private Set<ReservedRoom> reservedRooms;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id" , nullable = false)
    private Hotel hotel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name" , referencedColumnName = "user_name" ,nullable = false)
    private User user;
}
