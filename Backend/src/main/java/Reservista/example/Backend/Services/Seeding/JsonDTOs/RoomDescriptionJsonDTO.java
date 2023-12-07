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
public class RoomDescriptionJsonDTO {
    @JsonProperty("Header Title")
    private String headerTitle;

    @JsonProperty("Image Sources")
    private List<String> imageSources;

    @JsonProperty("Room Details")
    private List<String> roomDetails;

    @JsonProperty("Total Price")
    private String totalPrice;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("Taxes and Fees")
    private String fees;
}
