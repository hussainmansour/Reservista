package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Services.Cancellation.CancellationHandler;
import Reservista.example.Backend.Services.Cancellation.CancellationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CancellationController{


    @Autowired
    CancellationService cancellationService;

    @PostMapping("/cancel-reservation")
    public ResponseDTO<CancellationResponseDTO> cancelReservation(CancellationRequestDTO cancellationRequestDTO)  {
        return cancellationService.cancelReservation(cancellationRequestDTO);
    }



}
