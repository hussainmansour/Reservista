package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Cancellation.CancellationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class CancellationController{


    @Autowired
    CancellationService cancellationService;

    @PostMapping("/cancel-reservation")
    public ResponseEntity<Long> cancelReservation(@AuthenticationPrincipal String username,@RequestParam long reservationId) throws GlobalException {

        long refundedAmount = cancellationService.cancelReservation(username, reservationId);
        return  ResponseEntity.ok(refundedAmount);
    }

    @PostMapping("/get-refunded-amount")
    public ResponseEntity<Long> getRefundedAmount(@AuthenticationPrincipal String username,@RequestParam long reservationID) throws GlobalException {

        long refundedAmount = cancellationService.getRefundedAmount(username, reservationID);
        return  ResponseEntity.ok(refundedAmount);
    }



}
