package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.RoomDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.RoomSearchCriteriaDTO;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class RoomSearchService {

    @Autowired
    HotelRepository hotelRepository;

    private int getNumberOfAvailableRoomsByRoomDescriptionID(RoomDescription roomDescription, RoomSearchCriteriaDTO roomSearchCriteriaDTO){
        return roomDescription.getRoomCount() - hotelRepository.getNumberOfConflictedRooms(roomDescription.getId(), roomSearchCriteriaDTO.getCheckIn(), roomSearchCriteriaDTO.getCheckOut());
    }


    private List<RoomDTO> convertToRoomDTOList(List<RoomDescription> roomDescriptions, RoomSearchCriteriaDTO roomSearchCriteriaDTO) {
        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (RoomDescription room : roomDescriptions) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomDetails(room.getRoomDetails());
            roomDTO.setCapacity(room.getCapacity());
            roomDTO.setPrice(room.getPrice());
            roomDTO.setTitle(room.getTitle());
            roomDTO.setId(room.getId());
            roomDTO.setRoomAvailability(getNumberOfAvailableRoomsByRoomDescriptionID(room, roomSearchCriteriaDTO));
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    public HotelDTO getRoomsInSpecificHotel (RoomSearchCriteriaDTO roomSearchCriteriaDTO){
        List<RoomDescription> roomDescriptions = hotelRepository.findAvailableRooms(
                roomSearchCriteriaDTO.getHotelId(),
                roomSearchCriteriaDTO.getCheckIn(),
                roomSearchCriteriaDTO.getCheckOut(),
                roomSearchCriteriaDTO.getNumberOfRooms(),
                roomSearchCriteriaDTO.getNumberOfTravelers());


        Hotel hotel = hotelRepository.findById(roomSearchCriteriaDTO.getHotelId()).get();
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
        hotelDTO.setImages(hotel.getHotelImages());
        hotelDTO.setReviewCount(hotel.getReviewCount());
        hotelDTO.setStarRating(hotel.getStarRating());
        hotelDTO.setRooms(convertToRoomDTOList(roomDescriptions, roomSearchCriteriaDTO));

        return hotelDTO;
     }


}
