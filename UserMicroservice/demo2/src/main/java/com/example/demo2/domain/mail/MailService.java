package com.example.demo2.domain.mail;

import com.example.demo2.domain.dto.ContactDTO;
import com.example.demo2.domain.dto.ContacteMultipleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MailService {

        @Autowired
        private JavaMailSender mailSender;

        public void sendUserUpdateEmail(ContacteMultipleDTO user) {
            String username = user.getUtilizator().getNume() + " " + user.getUtilizator().getPrenume();
            String to = null;
            for(ContactDTO contactDTO: user.getContacte()){
                if(contactDTO.getTip().equals("email")){
                    to = contactDTO.getInformatie();
                }
            }
            if (to == null || to.isBlank()) {
                throw new IllegalStateException("Emailul destinatarului nu este setat!");
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Datele contului au fost actualizate");
            message.setText("Buna " + username + ", datele contului tau au fost actualizate de un administrator." + user);
            message.setFrom("bianca.floricica1@yahoo.com");

            mailSender.send(message);
        }
}
