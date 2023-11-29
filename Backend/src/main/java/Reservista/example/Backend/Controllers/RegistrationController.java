package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.Errors.CredentialsException;
import Reservista.example.Backend.Event.RegistrationCompleteEvent;
import Reservista.example.Backend.Models.User;
import Reservista.example.Backend.Services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    private UserRegistrationService userRegistrationService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequestDTO request) throws CredentialsException {

        User user = userRegistrationService.registerUser(request);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user
        ));
        return ResponseEntity.ok("registeration completed, verify your email");

    }


}
