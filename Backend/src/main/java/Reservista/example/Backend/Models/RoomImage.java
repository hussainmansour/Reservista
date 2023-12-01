package Reservista.example.Backend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_image")
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    @NotNull
    @Column(name = "source")
    private byte[] source;

    @ManyToOne
    @JoinColumn(name = "room_description_id",referencedColumnName = "id")
    private RoomDescription roomDescription;
}
