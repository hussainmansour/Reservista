package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
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


    private List<HotelDTO> convertToHotelDTOList(List<Hotel> hotels) {
        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for (Hotel hotel : hotels) {
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setId(hotel.getId());
            hotelDTO.setName(hotel.getName());
            hotelDTO.setCity(hotel.getLocation().getCity());
            hotelDTO.setRating(hotel.getRating());
            hotelDTO.setReviewCount(hotel.getReviewCount());
            hotelDTO.setStarRating(hotel.getStarRating());
            hotelDTO.setCountry(hotel.getLocation().getCountry());
            hotelDTO.setMinRoomPrice(calculateMinRoomPrice(hotel));
//            hotelDTO.setImages(hotel.getHotelImages()); // Assuming getHotelImages returns Set<HotelImage>

            hotelDTOList.add(hotelDTO);
        }

        return hotelDTOList;
    }

    public HotelSearchResultDTO getHotelsWithCriteria(SearchCriteriaDTO searchCriteria, Pageable pageable) {

        Page<Hotel> hotelPage;

        if (searchCriteria.hasSortCriteria()) {
            hotelPage = hotelSearchFactory.sortHotelsByCriteria(searchCriteria, pageable);
        } else {
            hotelPage = hotelSearchFactory.searchHotels(searchCriteria, pageable);
        }

        if (hotelPage == null) {
            return null;
        }
        List<HotelDTO> hotelDTOList = convertToHotelDTOList(hotelPage.getContent());
//        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOList, hotelPage.getPageable(), hotelPage.getTotalElements());

        HotelSearchResultDTO searchResult = new HotelSearchResultDTO();
        searchResult.setHotels(hotelDTOList);
        return searchResult;
    }

}
