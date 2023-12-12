package Reservista.example.Backend.Models.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_description")
public class RoomDescription {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Min(1)
    @Column(name = "capacity")
    private int capacity;

    @Min(1)
    @Column(name = "room_count")
    private int roomCount;

    @OneToMany(mappedBy = "roomDescription" , cascade = CascadeType.ALL)
    private Set<RoomImage> roomImages;

    @ElementCollection
    @CollectionTable(name = "room_details", joinColumns =
    @JoinColumn(name = "room_description_id" , referencedColumnName = "id"))
    @Column(name = "room_details" , nullable = false)
    private Set<String> roomDetails;


    @ManyToOne(optional = false , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id" , nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "roomDescription" , cascade = CascadeType.ALL)
    private Set<ReservedRoom> reservedRooms;
}
