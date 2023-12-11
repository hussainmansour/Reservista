package Reservista.example.Backend.DTOs.SearchAndFilter;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class SearchResultDTO {
    private Page<HotelDTO> hotels;
}
