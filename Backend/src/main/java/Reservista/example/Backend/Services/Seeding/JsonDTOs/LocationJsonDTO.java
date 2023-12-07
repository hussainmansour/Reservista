package Reservista.example.Backend.Services.Seeding.JsonDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationJsonDTO {
    @JsonProperty("location_data")
    private LocationDetailsJsonDTO locationDetails;
    private List<HotelJsonDTO> hotels;
}
