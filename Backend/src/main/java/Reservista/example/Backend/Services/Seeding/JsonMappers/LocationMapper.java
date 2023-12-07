package Reservista.example.Backend.Services.Seeding.JsonMappers;

import Reservista.example.Backend.Converters.ZoneIdConverter;
import Reservista.example.Backend.Models.Coordinates;
import Reservista.example.Backend.Models.Location;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.CoordinatesJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationJsonDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;

import java.time.ZoneId;

//@Mapper(componentModel = "spring", uses = ZoneIdConverter.class)
public interface LocationMapper {

//    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
//
//    @Mapping(source = "locationDetails.city" , target = "city")
//    @Mapping(source = "locationDetails.country", target = "country")
//    @Mapping(source = "locationDetails.timezone", target = "timeZone", qualifiedByName = "convertToZoneId")
//    @Mapping(source = "locationDetails.coordinates", target = "coordinates")
//    Location mapToLocation(LocationJsonDTO locationJsonDTO);
//
//    @Mapping(source = "longitude" , target = "longitude")
//    @Mapping(source = "latitude" , target = "latitude")
//    Coordinates mapToCoordinates(CoordinatesJsonDTO coordinatesJsonDTO);
//
//    @Named("convertToZoneId")
//    default ZoneId convertToZoneId(String timeZone) {
//        return new ZoneIdConverter().convertToEntityAttribute(timeZone);
//    }
}
