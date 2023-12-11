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

    @Min(1)
    private int numberOfRooms;

    @Min(1)
    private int numberOfTravelers;

    @Min(0)
    private int pageNumber;

    @NotNull
    private Instant checkIn;

    @NotNull
    private Instant checkOut;

    private double minPrice;
    private double maxPrice;
    private int minStars;
    private int maxStars;
    private double minRating;
    private double maxRating;
    private String sortBy;
    private String sortOrder;

    public boolean hasFilterCriteria() {
        return minPrice > 0 || maxPrice > 0 || minStars > 0 || maxStars > 0 || minRating > 0 || maxRating > 0;
    }

    public boolean hasSortCriteria() {
        return sortBy != null && !sortBy.isEmpty();
    }

    public PageRequest toPageRequest(int defaultPageSize) {
        int calculatedPageNumber = pageNumber >= 0 ? pageNumber : 0;
        int calculatedPageSize = defaultPageSize > 0 ? defaultPageSize : 20; // Set a default page size if needed
        return PageRequest.of(calculatedPageNumber, calculatedPageSize);
    }
}

