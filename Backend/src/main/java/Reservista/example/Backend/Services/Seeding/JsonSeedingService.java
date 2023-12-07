package Reservista.example.Backend.Services.Seeding;

import Reservista.example.Backend.Config.ImageUtil;
import Reservista.example.Backend.DAOs.LocationRepository;
import Reservista.example.Backend.Models.Location;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.LocationJsonDTO;
import Reservista.example.Backend.Services.Seeding.JsonMappers.LocationMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonSeedingService {

//    @Autowired
//    private LocationMapper locationMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private LocationRepository locationRepository;

    public void seedFromFile(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<LocationJsonDTO> locationJsonDTOs =
                    objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {});

            // todo: mapping
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // todo : mapping
    private void addToDB(List<Location> locations) {

    }

}
