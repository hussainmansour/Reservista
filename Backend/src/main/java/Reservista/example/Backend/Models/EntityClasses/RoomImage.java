package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_image")
public class RoomImage {

    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    @NotNull
    @Column(columnDefinition = "LONGBLOB")
    private byte[] source;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_description_id" , nullable = false)
    private RoomDescription roomDescription;
}
