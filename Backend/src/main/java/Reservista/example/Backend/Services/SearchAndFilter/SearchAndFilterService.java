package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class SearchAndFilterService {


    @Autowired
    HotelSearchService hotelSearchService;

    public HotelSearchResultDTO filterAndSortHotels(SearchCriteriaDTO searchCriteria){
        Pageable pageable = searchCriteria.toPageRequest();
        return hotelSearchService.getHotelsWithCriteria(searchCriteria, pageable);
    }



    //TODO: get hotel by name
    //TODO: make the home page
    //TODO: edit the hotel repo
}
