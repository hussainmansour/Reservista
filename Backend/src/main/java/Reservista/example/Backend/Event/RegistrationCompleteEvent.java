package Reservista.example.Backend.Event;

import Reservista.example.Backend.Models.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;



public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;

    public RegistrationCompleteEvent(User user){
        super(user);
        this.user=user;
    }

    public User getUser() {
        return this.user;
    }



}
