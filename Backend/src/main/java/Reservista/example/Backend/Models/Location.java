package Reservista.example.Backend.Models;

import Reservista.example.Backend.Converters.ZoneIdConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "country")
    private String country;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "time-zone")
    private ZoneId timeZone;

    @Embedded
    private Coordinates coordinates;

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private List<Hotel> rooms;
}
