package Reservista.example.Backend.Services.Seeding.JsonMappers;


import Reservista.example.Backend.Config.ImageUtil;
import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.HotelImageJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.HotelJsonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    @Autowired
    private RoomDescriptionMapper roomDescriptionMapper;

    public Hotel mapToHotel(HotelJsonDTO hotelJsonDTO) {
        boolean isFullyRefundable = new Random().nextBoolean();
        int fullyRefundableRate = getFullyRefundableRate(isFullyRefundable);

        Hotel hotel =  Hotel.builder()
                .name(hotelJsonDTO.getHotelTitle())
                .starRating(extractStarRating(hotelJsonDTO.getStarRating()))
                .rating(extractRating(hotelJsonDTO.getReviewRating()))
                .reviewCount(extractReviewCount(hotelJsonDTO.getReviewCount()))
                .address(hotelJsonDTO.getHotelLocation())
                .areaDescription(hotelJsonDTO.getExploreTheArea())
                .hotelImages(mapToHotelImageSet(hotelJsonDTO.getImages()))
                .hotelFoodOptions(generateRandomHotelFoodOptions())
                .isFullyRefundable(isFullyRefundable)
                .fullyRefundableRate(fullyRefundableRate)
                .roomDescriptions(roomDescriptionMapper.mapToRoomDescriptionSet(hotelJsonDTO.getRooms()))
                .build();

        hotel.getRoomDescriptions().forEach(roomDescription -> roomDescription.setHotel(hotel));
        hotel.getHotelImages().forEach(hotelImage -> hotelImage.setHotel(hotel));
        return hotel;
    }

    public Set<Hotel> mapToHotelSet(Set<HotelJsonDTO> hotelJsonDTOs) {
        return hotelJsonDTOs.stream()
                .map(this::mapToHotel)
                .collect(Collectors.toSet());
    }

    public HotelImage mapToHotelImage(HotelImageJsonDTO hotelImageJsonDTO) {
        return HotelImage.builder()
                .source(ImageUtil.convertImageUrlToBytes(hotelImageJsonDTO.getSource()))
                .caption(hotelImageJsonDTO.getCaption())
                .build();
    }

    public Set<HotelImage> mapToHotelImageSet(Set<HotelImageJsonDTO> hotelImageJsonDTOs) {
        return hotelImageJsonDTOs.stream()
                .map(this::mapToHotelImage)
                .collect(Collectors.toSet());
    }

    private HotelFoodOptions generateRandomHotelFoodOptions(){
        return HotelFoodOptions.builder()
                .breakfastPrice(getRandomInt(10,100))
                .dinnerPrice(getRandomInt(50,200))
                .lunchPrice(getRandomInt(30,180))
                .build();
    }

    // "star_rating": "5.0 star property"
    private int extractStarRating(String starRating) {
        return Arrays.stream(starRating.split(" "))
                .findFirst()
                .map(Double::parseDouble)
                .map(Double::intValue)
                .orElse(0);
    }

    private double extractRating(String rating) {
        return !rating.isEmpty() ?
                Double.parseDouble(rating) : 0;
    }

    private int extractReviewCount(String reviewCount) {
        reviewCount = reviewCount.replaceAll("," , "");
        return !reviewCount.isEmpty() ?
                Integer.parseInt(reviewCount) : 0;
    }

    private int getRandomInt(int from, int to) {
        Random random = new Random();
        return from + random.nextInt(to - from + 1);
    }

    private int getFullyRefundableRate(boolean isFullyRefundable){
        return isFullyRefundable ? getRandomInt(0,100) : 0;
    }
}
