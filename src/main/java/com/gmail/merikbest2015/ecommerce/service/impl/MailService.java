package com.gmail.merikbest2015.ecommerce.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${hostname}")
    private String hostname;

    public void sendMessageHtml(String to, String subject, String template, Map<String, Object> attributes) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            attributes.put("url", "http://" + hostname);
            Context thymeleafContext = new Context();
            thymeleafContext.setVariables(attributes);
            String htmlBody = thymeleafTemplateEngine.process("email/" + template, thymeleafContext);
            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(username);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(htmlBody, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        executor.shutdown();
    }
}
