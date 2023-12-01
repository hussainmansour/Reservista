package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.Services.OTPService;
import Reservista.example.Backend.responds.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class OTPController {
    @Autowired
    private OTPService otpService;
    @PostMapping("/verifyCode")
    public Respond verifyGmailAccount(@RequestParam("email") String email, @RequestParam("code")  String code) {
        return otpService.verifyGmailAccount(email,code);
    }

    @PostMapping("/refreshVerificationCode")
    public Respond refreshOTP(@RequestParam("email") String email){
        return otpService.refreshOTP(email);
    }
}
