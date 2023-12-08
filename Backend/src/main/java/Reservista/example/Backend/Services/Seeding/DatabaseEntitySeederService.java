package Reservista.example.Backend.Services.Seeding;


import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DAOs.LocationRepository;
import Reservista.example.Backend.DAOs.RoomDescriptionRepository;
import Reservista.example.Backend.DAOs.RoomRepository;
import Reservista.example.Backend.Models.EntityClasses.Location;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationDetailsJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonMappers.LocationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseEntitySeederService {

    private final LocationRepository locationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final RoomDescriptionRepository roomDescriptionRepository;
    private final LocationMapper locationMapper;


    public void addAllLocationsToDB(List<LocationDetailsJsonDTO> locationJsonDTOs) {
        locationJsonDTOs
                .stream()
                .map(locationMapper::mapToLocation)
                .forEach(locationRepository::save);
    }

    public void addHotelsToDB(List<LocationJsonDTO> locationJsonDTOs) {
        for (LocationJsonDTO locationJsonDTO : locationJsonDTOs) {
            Location location = locationMapper.mapToLocation(locationJsonDTO);
            saveLocationWithHotels(location);
        }
    }

    // bug: the persisting doesn't work
    private void saveLocationWithHotels(Location location) {
        locationRepository.save(location);
    }

}
