package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchResultDTO;
import Reservista.example.Backend.Models.Hotel;
import Reservista.example.Backend.Services.Mappers.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SearchAndFilterService {

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final HotelMapper hotelMapper;

    @Autowired
    private final HotelSearchFactory hotelSearchFactory;



    public SearchAndFilterService(HotelRepository hotelRepository, HotelMapper hotelMapper, HotelSearchFactory hotelSearchFactory) {
        this.hotelRepository = hotelRepository;
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

        SearchResultDTO searchResult = new SearchResultDTO();
        searchResult.setHotels(hotelPage.map(hotelMapper::hotelToHotelDTO));
        return searchResult;
    }

    //TODO: get hotel by name
    //TODO: make the home page
    //TODO: try make factory
}
