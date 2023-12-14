package Reservista.example.Backend.Models.EntityClasses;


import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "temp_reservation_details")
public class TempReservationDetails {

    @Id
    @OneToOne
    @JoinColumn(columnDefinition = "reservation_id" , referencedColumnName = "id")
    private Reservation reservation;

    @NotBlank
    @Column(name = "invoice")
    private String invoice;
}
