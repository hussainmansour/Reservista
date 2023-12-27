package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelIdentifierWithSearchCriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class SearchAndFilterService {


    @Autowired
    HotelSearchService hotelSearchService;

    @Autowired
    RoomSearchService roomSearchService;

    public HotelSearchResultDTO filterAndSortHotels(HotelSearchCriteriaDTO searchCriteria){
        Pageable pageable = searchCriteria.toPageRequest();
        return hotelSearchService.getHotelsWithCriteria(searchCriteria, pageable);
    }

    public HotelDTO filterRooms(HotelIdentifierWithSearchCriteriaDTO hotelIdentifierWithSearchCriteriaDTO){
        return roomSearchService.getRoomsInSpecificHotel(hotelIdentifierWithSearchCriteriaDTO);
    }

}
