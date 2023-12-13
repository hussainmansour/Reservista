package Reservista.example.Backend.Models.EmbeddedClasses;

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
public class Coordinates {
    private double longitude;
    private double latitude;
}
