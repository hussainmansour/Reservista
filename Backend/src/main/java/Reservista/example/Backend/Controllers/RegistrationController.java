package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Error.DeactivatedAccountException;
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
    public ResponseEntity<ResponseDTO<Void>> register(@Valid @RequestBody RegistrationRequestDTO request) throws RegistrationCredentialsException, DeactivatedAccountException {

        userRegistrationService.registerUser(request);

        return ResponseEntity
                .ok(StatusCode.SUCCESSFUL_REGISTRATION.getRespond());
    }


}
