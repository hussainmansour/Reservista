package Reservista.example.Backend.Config;


import Reservista.example.Backend.Services.Seeding.JsonSeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private boolean hasSeeded = false;

    @Autowired
    private JsonSeedingService jsonSeedingService;

    @Override
    public void run(String... args) throws Exception {
        if (!hasSeeded) {
            jsonSeedingService.seedHotelsFromFile(
                    System.getProperty("user.dir")
                    + "/src/main/resources/data.json");
            hasSeeded = true;
        }
    }
}
