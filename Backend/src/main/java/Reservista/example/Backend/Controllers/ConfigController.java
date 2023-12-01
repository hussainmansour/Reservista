package Reservista.example.Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private Set<String> validCountries;

    @GetMapping("/countries")
    public ResponseEntity<Set<String>> getValidCountries() {
        return ResponseEntity.ok(validCountries);
    }
}