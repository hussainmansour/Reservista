package Reservista.example.Backend.Services.Mappers;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "city", source = "location.city")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "reviewCount", source = "reviewCount")
    @Mapping(target = "starRating", source = "starRating")
    @Mapping(target = "country", source = "location.country")
    @Mapping(target = "minRoomPrice", expression = "java(calculateMinRoomPrice(hotel))")
    @Mapping(target = "images", source = "images")
    HotelDTO hotelToHotelDTO(Hotel hotel);

    default double calculateMinRoomPrice(Hotel hotel) {

        //TODO: implement this
        return 5;
    }
}
