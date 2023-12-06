package Reservista.example.Backend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "room_description")
public class RoomDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "price")
    private double price;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "roomDescription",cascade = CascadeType.ALL)
    private List<RoomImage> roomImages;

    @ElementCollection
    @CollectionTable(name = "room_details", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "room_details")
    private List<String> roomDetails;

    @OneToMany(mappedBy = "roomDescription",cascade = CascadeType.ALL)
    private List<Room> rooms;
}
