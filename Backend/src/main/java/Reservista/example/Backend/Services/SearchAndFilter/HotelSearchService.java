package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSummaryDTO;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelSearchService {

    @Autowired
    private HotelSearchFactory hotelSearchFactory;

    @Autowired
    HotelRepository hotelRepository;

    private int calculateMinRoomPrice(UUID id, HotelSearchCriteriaDTO searchCriteria) {
        List<RoomDescription> roomDescriptions = hotelRepository.findAvailableRooms(
                id,
                searchCriteria.getCheckIn(),
                searchCriteria.getCheckOut(),
                searchCriteria.getNumberOfRooms(),
                searchCriteria.getNumberOfTravelers()
        );

        return roomDescriptions.stream()
                .mapToInt(RoomDescription::getPrice)
                .min()
                .orElse(Integer.MAX_VALUE);
    }



    private List<HotelSummaryDTO> convertToHotelSummaryDTOList(List<Hotel> hotels, HotelSearchCriteriaDTO searchCriteria) {
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
            hotelSummaryDTO.setMinRoomPrice(calculateMinRoomPrice(hotel.getId(), searchCriteria));
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
        List<HotelSummaryDTO> hotelDTOList = convertToHotelSummaryDTOList(hotelSummaryPage.getContent(), searchCriteria);
        HotelSearchResultDTO searchResult = new HotelSearchResultDTO();
        searchResult.setHotels(hotelDTOList);
        return searchResult;
    }

}