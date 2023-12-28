package Reservista.example.Backend.DTOs.SearchAndFilter;


import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class HotelSummaryDTO {

    private UUID id;
    private String name;
    private String city;
    private String country;
    private double rating;
    private Set<String> imagesUrls;
    private int reviewCount;
    private int starRating;
    private double minRoomPrice;
}
