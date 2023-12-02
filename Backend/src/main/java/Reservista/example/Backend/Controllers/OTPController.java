package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.VerificationDTO;
import Reservista.example.Backend.Services.OTPService;
import Reservista.example.Backend.DTOs.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OTPController {
    @Autowired
    private OTPService otpService;
    @PostMapping("/verifyCode")
    public Respond verifyGmailAccount(@RequestBody VerificationDTO verificationDTO) {
        return otpService.verifyGmailAccount(verificationDTO.getEmail(),verificationDTO.getCode());
    }

    @PostMapping("/refreshVerificationCode")
    public Respond refreshOTP(@RequestBody String email){
        return otpService.refreshOTP(email);
    }
}
