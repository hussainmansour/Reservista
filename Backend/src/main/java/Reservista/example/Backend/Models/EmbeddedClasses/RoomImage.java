package Reservista.example.Backend.Models.EmbeddedClasses;

import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_image")
public class RoomImage {

    @Id
    private UUID id;

    @Lob
    @NotNull
    private byte[] source;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_description_id" , nullable = false)
    private RoomDescription roomDescription;
}
