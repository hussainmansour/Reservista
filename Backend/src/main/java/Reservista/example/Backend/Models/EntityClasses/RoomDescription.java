package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.EmbeddedClasses.RoomImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "room_description")
public class RoomDescription {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "price")
    private double price;

    @Column(name = "capacity")
    private int capacity;

    @ElementCollection
    @CollectionTable(name = "room_image", joinColumns =
    @JoinColumn(name = "room_description_id" , referencedColumnName = "id"))
    @AttributeOverrides({
            @AttributeOverride(
                    name = "source",
                    column = @Column(name = "room_image_source")
            ),
    })
    private Set<RoomImage> roomImages;

    @ElementCollection
    @CollectionTable(name = "room_details", joinColumns =
    @JoinColumn(name = "room_description_id" , referencedColumnName = "id"))
    private Set<String> roomDetails;

    @OneToMany(mappedBy = "roomDescription",cascade = CascadeType.ALL)
    private Set<Room> rooms;
}
