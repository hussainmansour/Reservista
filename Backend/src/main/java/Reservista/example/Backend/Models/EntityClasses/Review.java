package Reservista.example.Backend.Models.EntityClasses;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "body")
    private String body;

    @Min(0)
    @Max(10)
    @NotNull
    @Column(name = "rating")
    private double rating;

    @OneToOne(mappedBy = "review" , cascade = CascadeType.ALL)
    private Reservation reservation;

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL)
    private Set<Report> reports;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id",referencedColumnName = "id" , nullable = false)
    private Hotel hotel;
}
