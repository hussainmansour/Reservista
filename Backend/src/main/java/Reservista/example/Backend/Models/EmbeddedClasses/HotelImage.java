package Reservista.example.Backend.Models.EmbeddedClasses;

import Reservista.example.Backend.Models.EntityClasses.Hotel;
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
@Table(name = "hotel_image")
public class HotelImage {

    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    @NotNull
    @Column(name = "image_source" , nullable = false)
    private byte[] source;

    @Column(name = "image_caption" , nullable = false)
    private String caption;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id" , nullable = false)
    private Hotel hotel;
}
