package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Registration.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {


    @Autowired
    private UserRegistrationService userRegistrationService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequestDTO request) throws GlobalException {

        userRegistrationService.registerUser(request);

        return ResponseEntity
                .ok("successful registration");
    }


}
