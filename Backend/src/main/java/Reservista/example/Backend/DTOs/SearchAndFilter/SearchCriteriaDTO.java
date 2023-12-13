package Reservista.example.Backend.DTOs.SearchAndFilter;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class SearchCriteriaDTO {
    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @Min(1)
    private int numberOfRooms;

    @Min(1)
    private int numberOfTravelers;

    @Min(0)
    private int pageNumber;

    @Min(0)
    private int pageSize = 0;

    @NotNull
    private Instant checkIn;

    @NotNull
    private Instant checkOut;

    private int minPrice;
    private int maxPrice;
    private int minStars;
    private int maxStars;
    private double minRating;
    private double maxRating;
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

