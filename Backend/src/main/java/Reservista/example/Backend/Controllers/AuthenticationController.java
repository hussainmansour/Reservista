package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Request.AuthenticationRequestDTO;
import Reservista.example.Backend.Services.Login.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<String>
    login(@RequestBody AuthenticationRequestDTO authenticationRequest) {
        String token = authenticationService.authenticate(
                authenticationRequest.getUserNameOrEmail(),
                authenticationRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
