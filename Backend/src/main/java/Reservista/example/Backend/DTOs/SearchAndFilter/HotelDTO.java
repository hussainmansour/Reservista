package Reservista.example.Backend.DTOs.SearchAndFilter;

import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class HotelDTO {
    private UUID id;
    private String name;
    private String city;
    private String country;
    private double rating;
    private List<HotelImage> images;
    private int reviewCount;
    private int starRating;
    private double minRoomPrice;
}
