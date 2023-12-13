package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchResultDTO;
import Reservista.example.Backend.Services.SearchAndFilter.SearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/search")
public class SearchAndFilterController {

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @GetMapping("/hotels")
    public ResponseEntity<SearchResultDTO> filterAndSortHotels( @RequestBody SearchCriteriaDTO searchCriteria, Pageable pageable ) {
        SearchResultDTO searchResult = searchAndFilterService.filterAndSortHotels(searchCriteria, pageable);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
