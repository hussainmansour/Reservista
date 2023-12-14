package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.EntityClasses.Hotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
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
    @Column(name = "image_source" , columnDefinition = "LONGBLOB")
    private byte[] source;

    @NotBlank
    @Column(name = "image_caption")
    private String caption;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id" , nullable = false)
    private Hotel hotel;
}
