package Reservista.example.Backend.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_description_id",referencedColumnName = "id")
    private RoomDescription roomDescription;

    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "room_reservation",
            joinColumns = @JoinColumn(name = "room_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id" , referencedColumnName = "id")
    )
    private List<Reservation> reservations;
}
