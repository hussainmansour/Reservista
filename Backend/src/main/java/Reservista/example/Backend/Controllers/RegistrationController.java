package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.RegistrationResponseDTO;
import Reservista.example.Backend.Exceptions.CredentialsException;
import Reservista.example.Backend.Services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("/create-account")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO request) throws CredentialsException {

        String result = userRegistrationService.registerUser(request);
        return ResponseEntity.ok(RegistrationResponseDTO.builder().response(result).build());

    }


}
