package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")

public class ReservationController {

    @PostMapping("/confirm")
    public ResponseDTO<String> confirm(@Valid @RequestBody ReservationDTO request) {

       return null;
    }


//    @PostMapping("/cancel")
//    public ResponseDTO<String> cancelReservation(@Valid @RequestBody String reservationId) {
//
//        return null;
//    }

}
