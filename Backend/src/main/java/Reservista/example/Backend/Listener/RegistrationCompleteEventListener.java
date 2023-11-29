package Reservista.example.Backend.Listener;

import Reservista.example.Backend.Event.RegistrationCompleteEvent;
import Reservista.example.Backend.Models.User;
import org.springframework.context.ApplicationListener;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {



    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
    }
}
