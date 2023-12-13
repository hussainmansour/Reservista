package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchResultDTO;
import Reservista.example.Backend.Models.EntityClasses.*;
import Reservista.example.Backend.Services.Mappers.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SearchAndFilterService {
    private final HotelMapper hotelMapper;

    private final HotelSearchFactory hotelSearchFactory;

    @Autowired
    public SearchAndFilterService(HotelMapper hotelMapper, HotelSearchFactory hotelSearchFactory) {
        this.hotelMapper = hotelMapper;
        this.hotelSearchFactory = hotelSearchFactory;
    }

    public SearchResultDTO filterAndSortHotels(SearchCriteriaDTO searchCriteria, Pageable pageable) {

        Page<Hotel> hotelPage;

        if (searchCriteria.hasSortCriteria()) {
            hotelPage = hotelSearchFactory.sortHotelsByCriteria(searchCriteria, pageable);
        } else {
            hotelPage = hotelSearchFactory.searchHotels(searchCriteria, pageable);
        }

        if (hotelPage == null) {
            return null;
        }
        SearchResultDTO searchResult = new SearchResultDTO();
        searchResult.setHotels(hotelPage.map(hotelMapper::hotelToHotelDTO));
        return searchResult;
    }

    //TODO: get hotel by name
    //TODO: make the home page
    //TODO: edit the hotel repo
}
