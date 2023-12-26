package Reservista.example.Backend.DTOs.SearchAndFilter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RoomSearchCriteriaDTO {

    @NotNull
    @NotBlank
    private UUID HotelId;

    @NotNull
    @NotBlank
    private int numberOfRooms;

    @NotNull
    @NotBlank
    private int numberOfTravelers;

    @NotNull
    @NotBlank
    private Instant checkIn;

    @NotNull
    @NotBlank
    private Instant checkOut;
}
