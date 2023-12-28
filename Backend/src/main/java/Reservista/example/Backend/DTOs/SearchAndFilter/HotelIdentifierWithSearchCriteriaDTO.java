package Reservista.example.Backend.DTOs.SearchAndFilter;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class HotelIdentifierWithSearchCriteriaDTO {

    @NotNull
    private UUID HotelId;

    @Min(1)
    private int numberOfRooms;

    @Min(1)
    private int numberOfTravelers;

    @NotNull
    @FutureOrPresent
    private Instant checkIn;

    @NotNull
    @FutureOrPresent
    private Instant checkOut;
}
