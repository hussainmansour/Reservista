package Reservista.example.Backend.DTOs.SearchAndFilter;

import Reservista.example.Backend.Models.EntityClasses.RoomImage;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RoomDTO {

    private String title;
    private int price;
    private int capacity;
    private Set<String> roomDetails;
    private Set<String> imagesUrls;
    private int roomAvailability;
    private UUID id;
}
