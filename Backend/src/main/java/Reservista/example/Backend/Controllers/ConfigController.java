package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Config.LocationDTO;
import Reservista.example.Backend.Services.Config.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/config")
public class ConfigController {

    private Set<String> validCountries;
    private LocationService locationService;

    @GetMapping("/countries")
    public ResponseEntity<Set<String>> getValidCountries() {
        return ResponseEntity.ok(validCountries);
    }

    @GetMapping("/locations")
    public ResponseEntity<Set<LocationDTO>> getLocations(){
        return ResponseEntity.ok(locationService.getLocations());
    }
}