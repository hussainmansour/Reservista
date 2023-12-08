package Reservista.example.Backend.Services.Seeding;

import Reservista.example.Backend.DAOs.LocationRepository;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationDetailsJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonMappers.LocationMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
public class JsonSeedingService {

    private LocationMapper locationMapper;
    private LocationRepository locationRepository;
    private DatabaseEntitySeederService databaseEntitySeederService;


    public void seedAllLocationsFromFile(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<LocationDetailsJsonDTO> locationJsonDTOs =
                    objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {});
            databaseEntitySeederService.addAllLocationsToDB(locationJsonDTOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seedHotelsFromFile(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<LocationJsonDTO> locationJsonDTOs =
                    objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {});
            databaseEntitySeederService.addHotelsToDB(locationJsonDTOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
