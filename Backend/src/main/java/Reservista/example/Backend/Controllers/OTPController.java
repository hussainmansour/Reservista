package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Registration.CodeVerificationDTO;
import Reservista.example.Backend.DTOs.Registration.RefreshOTPDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Registration.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OTPController {
    @Autowired
    private OTPService otpService;

    @PostMapping("/verify-code")
    public ResponseEntity<Void> verifyGmailAccount(@RequestBody CodeVerificationDTO codeVerificationDTO) throws GlobalException {
        otpService.verifyGmailAccount(codeVerificationDTO.getEmail(), codeVerificationDTO.getCode());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/refresh-verification-code/{email}")
    public ResponseEntity<Void> refreshOTP(@PathVariable String email) throws GlobalException {
        System.out.println(email);
        otpService.refreshOTP(email);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}