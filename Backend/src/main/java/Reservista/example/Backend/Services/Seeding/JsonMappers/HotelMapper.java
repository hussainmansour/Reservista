package Reservista.example.Backend.Services.Seeding.JsonMappers;


import Reservista.example.Backend.Config.ImageUtil;
import Reservista.example.Backend.Models.EmbeddedClasses.HotelImage;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.Room;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.HotelImageJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.HotelJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.RoomDescriptionJsonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class HotelMapper {

    @Autowired
    private RoomDescriptionMapper roomDescriptionMapper;

    public Hotel mapToHotel(HotelJsonDTO hotelJsonDTO) {
        return Hotel.builder()
                .name(hotelJsonDTO.getHotelTitle())
                .starRating(starRatingToDouble(hotelJsonDTO.getStarRating()))
                .rating(stringToDouble(hotelJsonDTO.getReviewRating()))
                .reviewCount(stringToInt(hotelJsonDTO.getReviewCount()))
                .address(hotelJsonDTO.getHotelLocation())
                .areaDescription(hotelJsonDTO.getExploreTheArea())
//                .hotelImages(mapToHotelImageSet(hotelJsonDTO.getImages()))
                .rooms(mapRooms(hotelJsonDTO.getRooms()))
                .build();
    }

    public Set<Hotel> mapToHotelSet(Set<HotelJsonDTO> hotelJsonDTOs) {
        return hotelJsonDTOs.stream()
                .map(this::mapToHotel)
                .collect(Collectors.toSet());
    }


    public HotelImage mapToHotelImage(HotelImageJsonDTO hotelImageJsonDTO) {
        return HotelImage.builder()
                .source(urlToByteArray(hotelImageJsonDTO.getSource()))
                .caption(hotelImageJsonDTO.getCaption())
                .build();
    }

    public Set<HotelImage> mapToHotelImageSet(Set<HotelImageJsonDTO> hotelImageJsonDTOs) {
        return hotelImageJsonDTOs.stream()
                .map(this::mapToHotelImage)
                .collect(Collectors.toSet());
    }

    public Set<Room> mapRooms(Set<RoomDescriptionJsonDTO> roomDescriptionJsonDTOs) {
        Set<Room> rooms = new HashSet<>();

        roomDescriptionJsonDTOs.forEach(roomDescriptionDTO ->
                IntStream.range(0, 10).forEach(index ->
                        rooms.add(Room.builder()
                                .roomDescription(roomDescriptionMapper
                                .mapToRoomDescription(roomDescriptionDTO))
                                .build())
                )
        );

        return rooms;
    }

    private double starRatingToDouble(String starRating) {
        return Arrays.stream(starRating.split(" "))
                .findFirst()
                .map(Double::parseDouble)
                .orElse(3.0);
    }

    private double stringToDouble(String val) {
        return Double.parseDouble(val);
    }

    private int stringToInt(String val) {
        return Integer.parseInt(val);
    }

    private byte[] urlToByteArray(String source) {
        return ImageUtil.convertImageUrlToBytes(source);
    }

}
