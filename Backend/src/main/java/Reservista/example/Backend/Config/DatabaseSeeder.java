package Reservista.example.Backend.Config;


import Reservista.example.Backend.Services.Seeding.JsonSeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir")
            + "/src/main/resources";

    private static final String SEEDING_STATE_FILE = RESOURCES_PATH + "/seeding_state.txt";
    private static final String ALL_LOCATIONS_FILE = RESOURCES_PATH + "/all_locations.json";
    private static final String ALL_HOTELS_FILE = RESOURCES_PATH + "/all_hotels.json";

    @Autowired
    private JsonSeedingService jsonSeedingService;

    @Override
    public void run(String... args) {
        boolean hasSeeded = getSeedingState();
        if (!hasSeeded) {
//            jsonSeedingService.seedAllLocationsFromFile(ALL_LOCATIONS_FILE);
//            jsonSeedingService.seedHotelsFromFile(ALL_HOTELS_FILE);
            setSeedingState();
        }
    }

    private boolean getSeedingState() {
        try {
            Path path = Path.of(SEEDING_STATE_FILE);
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                return Boolean.parseBoolean(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void setSeedingState() {
        try {
            Path path = Path.of(SEEDING_STATE_FILE);
            Files.writeString(path, String.valueOf(true), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
