package Reservista.example.Backend.Controllers;


import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.CancellationExceptionHandler;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Services.Cancellation.CancellationHandler;
import Reservista.example.Backend.Services.Cancellation.CancellationService;
import Reservista.example.Backend.Services.Payment.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    CancellationService cancellationService;

    @PostMapping("/cancel")
    public ResponseDTO cancel(String reservationID) {
        // I have a question do we send the user ID in each request??
        return paymentService.cancelPayment(reservationID);
    }

    @PostMapping("/cancell")
    public ResponseDTO<CancellationResponseDTO> cancell(CancellationRequestDTO cancellationRequestDTO) throws CancellationExceptionHandler {
        // I have a question do we send the user ID in each request??
        return cancellationService.cancelReservation(cancellationRequestDTO);
    }



}
