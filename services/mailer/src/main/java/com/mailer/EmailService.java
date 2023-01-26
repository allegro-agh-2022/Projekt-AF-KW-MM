package com.mailer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String recipientAddress, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dwallegro.shop@gmail.com");
        message.setTo(recipientAddress);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
        System.out.println(String.format("Send message: %s", content));
    }
}
