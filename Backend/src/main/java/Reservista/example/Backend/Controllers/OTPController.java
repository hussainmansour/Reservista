package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.Services.OTPService;
import Reservista.example.Backend.responds.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class OTPController {
    @Autowired
    private OTPService otpService;
    @PostMapping("Auth/verifyCode")
    public Respond verifyGmailAccount(@RequestParam("email") String email, @RequestParam("code")  String code) {
        return otpService.verifyGmailAccount(email,code);
    }
    @PostMapping("Auth/refreshVerificationCode")
    public Respond refreshOTP(@RequestParam("email") String email){
        return otpService.refreshOTP(email);
    }
}
