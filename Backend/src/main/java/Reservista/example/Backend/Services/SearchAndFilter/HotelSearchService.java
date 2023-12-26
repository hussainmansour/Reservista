package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSummaryDTO;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelSearchService {

    @Autowired
    private HotelSearchFactory hotelSearchFactory;

    private int calculateMinRoomPrice(Hotel hotel) {
//        int minPrice = Integer.MAX_VALUE;
//        for(RoomDescription rd :hotel.getRoomDescriptions()){
//            minPrice = Math.min(minPrice, rd.getPrice());
//        }
//        return minPrice;
        return 5;
    }


    private List<HotelSummaryDTO> convertToHotelSummaryDTOList(List<Hotel> hotels) {
        List<HotelSummaryDTO> hotelSummaryDTOList = new ArrayList<>();

        for (Hotel hotel : hotels) {
            HotelSummaryDTO hotelSummaryDTO = new HotelSummaryDTO();
            hotelSummaryDTO.setId(hotel.getId());
            hotelSummaryDTO.setName(hotel.getName());
            hotelSummaryDTO.setCity(hotel.getLocation().getCity());
            hotelSummaryDTO.setRating(hotel.getRating());
            hotelSummaryDTO.setReviewCount(hotel.getReviewCount());
            hotelSummaryDTO.setStarRating(hotel.getStarRating());
            hotelSummaryDTO.setCountry(hotel.getLocation().getCountry());
            hotelSummaryDTO.setMinRoomPrice(calculateMinRoomPrice(hotel));
//            hotelSummaryDTO.setImages(hotel.getHotelImages()); // Assuming getHotelImages returns Set<HotelImage>
            hotelSummaryDTOList.add(hotelSummaryDTO);
        }
        return hotelSummaryDTOList;
    }

    public HotelSearchResultDTO getHotelsWithCriteria(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {

        Page<Hotel> hotelSummaryPage;

        if (searchCriteria.hasSortCriteria()) {
            hotelSummaryPage = hotelSearchFactory.sortHotelsByCriteria(searchCriteria, pageable);
        } else {
            hotelSummaryPage = hotelSearchFactory.searchHotels(searchCriteria, pageable);
        }

        if (hotelSummaryPage == null) {
            return null;
        }
        List<HotelSummaryDTO> hotelDTOList = convertToHotelSummaryDTOList(hotelSummaryPage.getContent());
        HotelSearchResultDTO searchResult = new HotelSearchResultDTO();
        searchResult.setHotels(hotelDTOList);
        return searchResult;
    }

}
