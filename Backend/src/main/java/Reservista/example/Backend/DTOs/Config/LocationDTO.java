package Reservista.example.Backend.DTOs.Config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LocationDTO {
    private String city;
    private String country;
}
