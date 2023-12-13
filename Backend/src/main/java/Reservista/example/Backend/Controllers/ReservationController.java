package Reservista.example.Backend.Controllers;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Services.Reservation.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @PostMapping("/apply_voucher")
    public ResponseDTO<Integer> applyVoucher(@AuthenticationPrincipal String username, @RequestBody String voucherCode){
        return reservationService.applyVoucher(username, voucherCode);
    }

    @PostMapping("/reserve")
    public ResponseDTO<ReservationResponseDTO> reserve(@AuthenticationPrincipal String userName, @Valid @RequestBody ReservationDTO reservationDTO) {
       return reservationService.reserve(userName,reservationDTO);
    }


//    @PostMapping("/cancel")
//    public ResponseDTO<String> cancelReservation(@Valid @RequestBody String reservationId) {
//
//        return null;
//    }
}
