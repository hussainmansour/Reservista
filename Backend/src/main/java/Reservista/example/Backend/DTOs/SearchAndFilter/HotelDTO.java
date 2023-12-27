package Reservista.example.Backend.DTOs.SearchAndFilter;

import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class HotelDTO {
    private UUID id;
    private String name;
    private String city;
    private String country;
    private String address;
    private int fullyRefundableRate;
    private boolean isFullyRefundable;
    private HotelFoodOptions hotelFoodOptions;
    private double rating;
    private Set<HotelImage> images;
    private int reviewCount;
    private int starRating;
    private List<RoomDTO> rooms;
}
