package Reservista.example.Backend.DTOs.SearchAndFilter;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class HotelIdentifierWithSearchCriteriaDTO {

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
    @FutureOrPresent
    private Instant checkIn;

    @NotNull
    @FutureOrPresent
    private Instant checkOut;
}
