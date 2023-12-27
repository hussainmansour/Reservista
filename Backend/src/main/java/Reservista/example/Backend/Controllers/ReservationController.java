package Reservista.example.Backend.Controllers;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Reservation.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/apply-voucher")
    public ResponseEntity<Integer> applyVoucher(@AuthenticationPrincipal String username, @RequestParam String voucherCode) throws GlobalException {
        Integer n = reservationService.applyVoucher(username, voucherCode);
        return ResponseEntity.ok(n);
    }

    @PostMapping("/reserve")
    public ReservationResponseDTO reserve(@AuthenticationPrincipal String username, @Valid @RequestBody ReservationDTO reservationDTO) throws GlobalException {
       return reservationService.reserve(username,reservationDTO);
    }

    @PostMapping("/rollback")
    public void rollback(@RequestParam long reservationId) {
        reservationService.rollbackReservation(reservationId);
    }

}
