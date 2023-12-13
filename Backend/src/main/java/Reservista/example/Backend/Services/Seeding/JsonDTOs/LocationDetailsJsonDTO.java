package Reservista.example.Backend.Services.Seeding.JsonDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDetailsJsonDTO {
    private String city;
    private String country;
    private String timezone;
    private String population;
    private CoordinatesJsonDTO coordinates;
}
