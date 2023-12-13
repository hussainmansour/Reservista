package Reservista.example.Backend.Services.Seeding.JsonDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmenityJsonDTO {

    @JsonProperty("heading")
    private String heading;

    @JsonProperty("bullet_points")
    private Set<String> bulletPoints;
}
