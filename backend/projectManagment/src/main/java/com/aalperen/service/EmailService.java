package com.aalperen.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


public interface EmailService {

    void sendVerificationEmail(String userEmail, String link) throws MessagingException;
}
