
package com.aalperen.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String userEmail, String link) throws MessagingException {
        String subject = "Verification Email From Aalperen";
        String text = "Click link to join the team "+ link;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setSubject(subject);
        helper.setText(text);
        helper.setTo(userEmail);
        try {

            mailSender.send(mimeMessage);


        }catch (MailException e) {
            throw new MailSendException("Failed send email");
        }
    }
}
