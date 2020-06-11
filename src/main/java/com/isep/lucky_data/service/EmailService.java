package com.isep.lucky_data.service;

import com.isep.lucky_data.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendNewPassword(ApplicationUser user, String newPassword) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String content = "<p>Bonjour " + user.getLastName() + " " + user.getFirstName() + ",</p>";
        content += "<p>Suite à votre demande, voici votre nouveau mot de passe : <strong>" + newPassword + "</strong>.</p>";
        content += "<p>N'oubliez pas de le modifier ensuite pour plus de sécurité.</p>";
        content += "<br><p>Cordialement,</p>";
        content += "<p>L'équipe Lucky Data</p>";

        helper.setTo(user.getEmail());
        helper.setSubject("Réinitialisation de votre mot de passe Lucky Data");
        helper.setText(content, true);

        emailSender.send(message);

    }
}
