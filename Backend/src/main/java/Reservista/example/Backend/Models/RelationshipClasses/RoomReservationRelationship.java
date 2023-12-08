package Reservista.example.Backend.Models.RelationshipClasses;


import Reservista.example.Backend.Models.EmbeddedClasses.RoomOptions;
import Reservista.example.Backend.Models.EntityClasses.Room;
import Reservista.example.Backend.Models.IDClasses.RoomReservationRelationshipId;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_reservation")
@IdClass(RoomReservationRelationshipId.class)
public class RoomReservationRelationship {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id" , referencedColumnName = "id")
    private Reservation reservation;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id" , referencedColumnName = "id")
    private Room room;

    @NotNull
    @Column(name = "reserved_room_price")
    private double reservedRoomPrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "breakfast",
                    column = @Column(name = "room_options_breakfast")
            ),
            @AttributeOverride(
                    name = "lunch",
                    column = @Column(name = "room_options_lunch")
            ),
            @AttributeOverride(
                    name = "dinner",
                    column = @Column(name = "room_options_dinner")
            ),
            @AttributeOverride(
                    name = "refundableRate",
                    column = @Column(name = "room_options_refundable_rate")
            ),
    })
    private RoomOptions roomOptions;
}
