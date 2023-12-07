package Reservista.example.Backend.Services.Seeding.JsonDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelImageJsonDTO {
    @JsonProperty("src")
    private String source;

    private String caption;
}
