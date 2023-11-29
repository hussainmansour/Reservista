package Reservista.example.Backend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "review_id",
            referencedColumnName = "id"
    )
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_name" , referencedColumnName = "user_name")
    private User user;

    @ManyToMany(mappedBy = "reservations")
    private List<Room> rooms;
}
