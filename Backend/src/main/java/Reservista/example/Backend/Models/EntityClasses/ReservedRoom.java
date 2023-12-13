package Reservista.example.Backend.Models.EntityClasses;
import Reservista.example.Backend.Models.EmbeddedClasses.RoomFoodOptions;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reserved_room")
public class ReservedRoom {

    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private RoomFoodOptions roomFoodOptions;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_description_id",referencedColumnName = "id" , nullable = false)
    private RoomDescription roomDescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id",referencedColumnName = "id" , nullable = false)
    private Reservation reservation;
}
