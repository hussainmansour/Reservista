package Reservista.example.Backend.Models.EntityClasses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_description_id",referencedColumnName = "id" , nullable = false)
    private RoomDescription roomDescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id",referencedColumnName = "id" , nullable = false)
    private Hotel hotel;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "room_reservation",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    )
    private Set<Reservation> reservations;
}
