package Reservista.example.Backend.Listener;

import Reservista.example.Backend.Event.RegistrationCompleteEvent;
import Reservista.example.Backend.Models.User;
import Reservista.example.Backend.Services.OTPService;
import Reservista.example.Backend.responds.Respond;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Log4j2
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private OTPService otpService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        Respond respond = otpService.createAndSendOTP(user);
        log.info(respond.getMessage());
    }
}