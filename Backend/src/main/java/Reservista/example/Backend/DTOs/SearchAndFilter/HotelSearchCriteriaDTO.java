package Reservista.example.Backend.DTOs.SearchAndFilter;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.PageRequest;

@Data
public class HotelSearchCriteriaDTO {
    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String country;

    @Min(1)
    private int numberOfRooms;

    @Min(1)
    private int numberOfTravelers;

    @Min(0)
    private int pageNumber;

    @Min(1)
    private int pageSize = 1;

    @NotNull
    @FutureOrPresent
    private Instant checkIn = Instant.now();

    @NotNull
    @FutureOrPresent
    private Instant checkOut = Instant.now().plus(2, ChronoUnit.DAYS);

    private int minPrice = 0;
    private int maxPrice = 10000;
    private int minStars = 0;
    private int maxStars = 5;
    private double minRating = 0;
    private double maxRating = 10;
    private String sortBy;
    private String sortOrder;

    public boolean hasSortCriteria() {
        return sortBy != null && !sortBy.isEmpty();
    }

    public PageRequest toPageRequest() {
        final int DEFAULT_PAGE_SIZE = 20;
        final int DEFAULT_PAGE_NUMBER = 0;
        int calculatedPageNumber = pageNumber >= 0 ? pageNumber : DEFAULT_PAGE_NUMBER;
        int calculatedPageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE; // Set a default page size if needed
        return PageRequest.of(calculatedPageNumber, calculatedPageSize);
    }
}

