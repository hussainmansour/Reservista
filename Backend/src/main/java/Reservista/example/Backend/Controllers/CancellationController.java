package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Cancellation.CancellationRequestDTO;
import Reservista.example.Backend.DTOs.Cancellation.CancellationResponseDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Cancellation.CancellationHandler;
import Reservista.example.Backend.Services.Cancellation.CancellationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<Long> cancelReservation(@AuthenticationPrincipal String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        long refundedAmount = cancellationService.cancelReservation(username, cancellationRequestDTO);
        return  ResponseEntity.ok(refundedAmount);
    }

    @PostMapping("/get-refunded-amount")
    public ResponseEntity<Long> getRefundedAmount(@AuthenticationPrincipal String username, CancellationRequestDTO cancellationRequestDTO) throws GlobalException {

        long refundedAmount = cancellationService.getRefundedAmount(username, cancellationRequestDTO);
        return  ResponseEntity.ok(refundedAmount);
    }



}
