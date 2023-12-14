package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.RoomSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.RoomSearchResultDTO;
import Reservista.example.Backend.Services.SearchAndFilter.SearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/search")
public class SearchAndFilterController {

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @GetMapping("/hotels")
    public ResponseEntity<HotelSearchResultDTO> filterAndSortHotels(@RequestBody HotelSearchCriteriaDTO searchCriteria) {
        HotelSearchResultDTO searchResult = searchAndFilterService.filterAndSortHotels(searchCriteria);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }


    @GetMapping("/rooms")
    public ResponseEntity<RoomSearchResultDTO> filterRooms(@RequestBody RoomSearchCriteriaDTO searchCriteria) {
        RoomSearchResultDTO searchResult = searchAndFilterService.filterRooms(searchCriteria);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
