package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.RoomDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelIdentifierWithSearchCriteriaDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RoomSearchService {

    @Autowired
    HotelRepository hotelRepository;

    private int getNumberOfAvailableRoomsByRoomDescriptionID(RoomDescription roomDescription, HotelIdentifierWithSearchCriteriaDTO hotelIdentifierWithSearchCriteriaDTO){
        return roomDescription.getRoomCount() - hotelRepository.getNumberOfConflictedRooms(roomDescription.getId(), hotelIdentifierWithSearchCriteriaDTO.getCheckIn(), hotelIdentifierWithSearchCriteriaDTO.getCheckOut());
    }


    private List<RoomDTO> convertToRoomDTOList(List<RoomDescription> roomDescriptions, HotelIdentifierWithSearchCriteriaDTO hotelIdentifierWithSearchCriteriaDTO) {
        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (RoomDescription room : roomDescriptions) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomDetails(room.getRoomDetails());
            roomDTO.setCapacity(room.getCapacity());
            roomDTO.setPrice(room.getPrice());
            roomDTO.setTitle(room.getTitle());
            roomDTO.setId(room.getId());
            roomDTO.setRoomAvailability(getNumberOfAvailableRoomsByRoomDescriptionID(room, hotelIdentifierWithSearchCriteriaDTO));

            Set<String> imageUrls = room.getRoomImages().stream()
                    .map(image -> getRoomImageUrl(image.getId()))
                    .collect(Collectors.toSet());
            roomDTO.setImagesUrls(imageUrls);

            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    private String getRoomImageUrl(UUID imageId) {
        // Replace this with your logic to generate image URLs
        return "http://localhost:8080/api/images/room?id=" + imageId;
    }

    private String getHotelImageUrl(UUID imageId) {
        // Replace this with your logic to generate image URLs
        return "http://localhost:8080/api/images/hotel?id=" + imageId;
    }

 
    public HotelDTO getRoomsInSpecificHotel (HotelIdentifierWithSearchCriteriaDTO hotelIdentifierWithSearchCriteriaDTO) throws GlobalException {
        List<RoomDescription> roomDescriptions = hotelRepository.findAvailableRooms(
                hotelIdentifierWithSearchCriteriaDTO.getHotelId(),
                hotelIdentifierWithSearchCriteriaDTO.getCheckIn(),
                hotelIdentifierWithSearchCriteriaDTO.getCheckOut(),
                hotelIdentifierWithSearchCriteriaDTO.getNumberOfRooms(),
                hotelIdentifierWithSearchCriteriaDTO.getNumberOfTravelers());


 
        Hotel hotel = hotelRepository
                .findById(hotelIdentifierWithSearchCriteriaDTO.getHotelId())
                .orElseThrow(() -> new GlobalException(ErrorCode.HOTEL_NOT_FOUND, HttpStatus.NOT_FOUND));
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setCity(hotel.getLocation().getCity());
        hotelDTO.setCountry(hotel.getLocation().getCountry());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setFullyRefundableRate(hotel.getFullyRefundableRate());
        hotelDTO.setFullyRefundable(hotel.isFullyRefundable());
        hotelDTO.setHotelFoodOptions(hotel.getHotelFoodOptions());
        hotelDTO.setRating(hotel.getRating());

        Set<String> imageUrls = hotel.getHotelImages().stream()
                .map(image -> getHotelImageUrl(image.getId()))
                .collect(Collectors.toSet());
        hotelDTO.setImagesUrls(imageUrls);

 
        hotelDTO.setReviewCount(hotel.getReviewCount());
        hotelDTO.setStarRating(hotel.getStarRating());
        hotelDTO.setRooms(convertToRoomDTOList(roomDescriptions, hotelIdentifierWithSearchCriteriaDTO));

        return hotelDTO;
     }


}
