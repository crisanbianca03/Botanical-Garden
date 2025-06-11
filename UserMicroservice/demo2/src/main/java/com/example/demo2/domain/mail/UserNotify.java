package com.example.demo2.domain.mail;

import com.example.demo2.domain.dto.ContacteMultipleDTO;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class UserNotify extends Observable {
    private static UserNotify instance;

    private UserNotify(){

    }

    public static synchronized UserNotify getInstance() {
        if (instance == null) {
            instance = new UserNotify();
        }
        return instance;
    }

    public void userUpdated(ContacteMultipleDTO user) {
        setChanged();
        notifyObservers(user);
    }
}
