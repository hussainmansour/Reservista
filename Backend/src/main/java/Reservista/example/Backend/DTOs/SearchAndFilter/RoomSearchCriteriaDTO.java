package Reservista.example.Backend.DTOs.SearchAndFilter;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RoomSearchCriteriaDTO {

    private UUID HotelId;
    private int numberOfRooms;
    private int numberOfTravelers;
    private Instant checkIn;
    private Instant checkOut;
}
