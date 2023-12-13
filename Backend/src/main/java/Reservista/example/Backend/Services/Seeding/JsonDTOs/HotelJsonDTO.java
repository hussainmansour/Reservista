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
public class HotelJsonDTO {
    @JsonProperty("hotel_title")
    private String hotelTitle;

    @JsonProperty("star_rating")
    private String starRating;

    @JsonProperty("review_rating")
    private String reviewRating;

    @JsonProperty("review_count")
    private String reviewCount;

    @JsonProperty("hotel_location")
    private String hotelLocation;

    @JsonProperty("explore_the_area")
    private Set<String> exploreTheArea;

    private Set<AmenityJsonDTO> amenities;
    private Set<HotelImageJsonDTO> images;
    private Set<RoomDescriptionJsonDTO> rooms;
}
