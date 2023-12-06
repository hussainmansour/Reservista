package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Registration.CodeVerificationDTO;
import Reservista.example.Backend.Services.Registration.OTPService;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OTPController {
    @Autowired
    private OTPService otpService;
    @PostMapping("/verify-code")
    public ResponseDTO verifyGmailAccount(@RequestBody CodeVerificationDTO codeVerificationDTO) {
        return otpService.verifyGmailAccount(codeVerificationDTO.getEmail(), codeVerificationDTO.getCode());
    }

    @PostMapping("/refresh-verification-code")
    public ResponseDTO refreshOTP(@RequestBody String email){
        return otpService.refreshOTP(email);
    }
}