//package Reservista.example.Backend.Services.SearchAndFilter;
//
//import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
//import Reservista.example.Backend.DTOs.SearchAndFilter.SearchResultDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class HotelSelectionService {
//
//    private final SearchAndFilterService searchAndFilterService;
//
//    @Autowired
//    public HotelSelectionService(SearchAndFilterService searchAndFilterService) {
//        this.searchAndFilterService = searchAndFilterService;
//    }
//
//    public SearchResultDTO getSelectedHotelResults(SearchCriteriaDTO searchCriteria) {
//        // You can add additional logic if needed before calling the search service
//        // For example, you might want to prioritize the selected hotel in the search results
//        return searchAndFilterService.filterAndSortHotels(searchCriteria, null);
//    }
//}
