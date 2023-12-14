package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Request.AuthenticationRequestDTO;
import Reservista.example.Backend.DTOs.Response.AuthenticationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Services.Login.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthenticationResponseDTO>>
    login(@RequestBody AuthenticationRequestDTO authenticationRequest) {
        System.out.println(authenticationRequest.toString());
        String token = authenticationService.authenticate(
                authenticationRequest.getUserNameOrEmail(),
                authenticationRequest.getPassword());
        return ResponseEntity.ok(ResponseDTO.<AuthenticationResponseDTO>builder()
                .status(StatusCode.SUCCESSFUL_LOGIN.getCode())
                .message(StatusCode.SUCCESSFUL_LOGIN.getMessage())
                .data(new AuthenticationResponseDTO(token))
                .build());
    }
}
