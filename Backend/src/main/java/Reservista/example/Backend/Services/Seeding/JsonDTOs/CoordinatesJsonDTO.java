package Reservista.example.Backend.Services.Seeding.JsonDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesJsonDTO {
    private double longitude;
    private double latitude;
}
