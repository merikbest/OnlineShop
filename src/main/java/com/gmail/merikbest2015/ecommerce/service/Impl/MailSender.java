package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSender {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${hostname}")
    private String hostname;

    public void sendEmail(User user) {
        String message = String.format("Hello, %s! \n " +
                        "Welcome to Perfume online store." +
                        "To complete registration please follow the link: http://%s/registration/activate/%s",
                user.getFirstName(),
                hostname,
                user.getActivationCode()
        );
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Activation code");
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
