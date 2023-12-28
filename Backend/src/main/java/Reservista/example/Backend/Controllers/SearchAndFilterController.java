package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelIdentifierWithSearchCriteriaDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.SearchAndFilter.SearchAndFilterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/search")
public class SearchAndFilterController {

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @PostMapping("/hotels")
    public ResponseEntity<HotelSearchResultDTO> filterAndSortHotels(@Valid @RequestBody HotelSearchCriteriaDTO searchCriteria) {
        HotelSearchResultDTO searchResult = searchAndFilterService.filterAndSortHotels(searchCriteria);
        return ResponseEntity.ok(searchResult);
    }


    @PostMapping("/hotel")
    public ResponseEntity<HotelDTO> getHotel(@Valid @RequestBody HotelIdentifierWithSearchCriteriaDTO searchCriteria) throws GlobalException {
        HotelDTO searchResult = searchAndFilterService.filterRooms(searchCriteria);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
