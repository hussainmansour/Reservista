package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Converters.ZoneIdConverter;
import Reservista.example.Backend.Models.EmbeddedClasses.Coordinates;
import Reservista.example.Backend.Models.IDClasses.LocationId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
@IdClass(LocationId.class)
public class Location {

    @Id
    @Column(name = "city")
    private String city;

    @Id
    @Column(name = "country")
    private String country;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "time_zone")
    private ZoneId timeZone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "longitude",
                    column = @Column(name = "coordinates_longitude")
            ),
            @AttributeOverride(
                    name = "latitude",
                    column = @Column(name = "coordinates_latitude")
            ),
    })
    private Coordinates coordinates;

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private Set<Hotel> hotels;
}
