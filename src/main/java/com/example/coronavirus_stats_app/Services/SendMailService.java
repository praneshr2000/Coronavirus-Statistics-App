package com.example.coronavirus_stats_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class SendMailService {
    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @PostConstruct
    public void sendEmail() throws UnsupportedEncodingException, MessagingException, MessagingException {
        /*
        * If you get email address not verified, you have to first deploy the app to get the website url
        * , and then you can apply to move out of sandbox in aws ses, so you can send messages to
        * unverified email addresses.
        * */
        String recipient = "covid.app.info@gmail.com";
        String subject = "Daily Coronavirus Update";
        String content = "<p>Hi there, this is a test email.</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress);
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
