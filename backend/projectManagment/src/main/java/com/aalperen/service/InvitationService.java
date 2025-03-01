package com.aalperen.service;

import com.aalperen.entity.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    void sendInvitation(String email, Long projectId) throws MessagingException;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserEmail(String userEmail);

    void deleteToToken(String token);
}
