package Reservista.example.Backend.Models.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price")
    private int price;


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
    
    @Column(name = "payment_intent_id", unique = true, nullable = true)
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

    @OneToMany(mappedBy = "reservation" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ReservedRoom> reservedRooms;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id" , nullable = false)
    private Hotel hotel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name" , referencedColumnName = "user_name" ,nullable = false)
    private User user;
}
