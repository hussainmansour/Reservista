package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.RegistrationResponseDTO;
import Reservista.example.Backend.StatusCode;
import Reservista.example.Backend.Errors.CredentialsException;
import Reservista.example.Backend.Event.RegistrationCompleteEvent;
import Reservista.example.Backend.Models.User;
import Reservista.example.Backend.Services.UserRegistrationService;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;

@RestController
public class RegistrationController {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO request) throws CredentialsException {


        User user = userRegistrationService.registerUser(request);


        System.out.println(userRepository.findIsValidatedByEmail(request.getEmail()));
        return ResponseEntity.ok(RegistrationResponseDTO.builder().response(StatusCode.SUCCESSFUL_REGISTRATION.getMessage()).build());

    }


}
