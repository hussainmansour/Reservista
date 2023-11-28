package Reservista.example.Backend.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
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
public class Coordinates {
    private double longitude;
    private double latitude;
}
