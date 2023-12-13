package Reservista.example.Backend.Services.Seeding.JsonMappers;

import Reservista.example.Backend.Converters.ZoneIdConverter;
import Reservista.example.Backend.Models.EmbeddedClasses.Coordinates;
import Reservista.example.Backend.Models.EntityClasses.Location;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.CoordinatesJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationDetailsJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationJsonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class LocationMapper {

    @Autowired
    private HotelMapper hotelMapper;

    public Location mapToLocation(LocationJsonDTO locationJsonDTO) {
        Location location = Location.builder()
                .city(locationJsonDTO.getLocationDetails().getCity())
                .country(locationJsonDTO.getLocationDetails().getCountry())
                .timeZone(convertToZoneId(locationJsonDTO.getLocationDetails().getTimezone()))
                .coordinates(mapToCoordinates(locationJsonDTO.getLocationDetails().getCoordinates()))
                .hotels(hotelMapper.mapToHotelSet(locationJsonDTO.getHotels()))
                .build();

        location.getHotels().forEach(hotel -> hotel.setLocation(location));
        return location;
    }

    public Location mapToLocation(LocationDetailsJsonDTO locationDetailsJsonDTO) {
        return Location.builder()
                .city(locationDetailsJsonDTO.getCity())
                .country(locationDetailsJsonDTO.getCountry())
                .timeZone(convertToZoneId(locationDetailsJsonDTO.getTimezone()))
                .coordinates(mapToCoordinates(locationDetailsJsonDTO.getCoordinates()))
                .build();
    }

    public Coordinates mapToCoordinates(CoordinatesJsonDTO coordinatesJsonDTO) {
        return Coordinates.builder()
                .longitude(coordinatesJsonDTO.getLongitude())
                .latitude(coordinatesJsonDTO.getLatitude())
                .build();
    }

    private ZoneId convertToZoneId(String timeZone) {
        return new ZoneIdConverter().convertToEntityAttribute(timeZone);
    }
}
