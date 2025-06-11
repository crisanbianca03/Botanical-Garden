package com.example.demo2.domain.mail;

import com.example.demo2.domain.dto.ContacteMultipleDTO;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class MailObserver implements Observer {
    private final MailService mailService;

    public MailObserver(MailService emailService) {
        this.mailService = emailService;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ContacteMultipleDTO user) {
            mailService.sendUserUpdateEmail(user);
        }
    }
}
