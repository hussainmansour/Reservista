package Reservista.example.Backend.Services.Config;


import Reservista.example.Backend.DAOs.LocationRepository;
import Reservista.example.Backend.DTOs.Config.LocationDTO;
import Reservista.example.Backend.Models.EntityClasses.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;

    public Set<LocationDTO> getLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(
                        location -> LocationDTO.builder()
                                .city(location.getCity())
                                .country(location.getCountry())
                                .build()
                )
                .collect(Collectors.toSet());
    }

}
