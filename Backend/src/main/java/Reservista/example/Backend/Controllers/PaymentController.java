package Reservista.example.Backend.Controllers;


import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Services.Payment.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/cancel")
    public ResponseDTO cancel(String reservationID) {
        // I have a question do we send the user ID in each request??
        return paymentService.cancelPayment(reservationID);
    }

}
