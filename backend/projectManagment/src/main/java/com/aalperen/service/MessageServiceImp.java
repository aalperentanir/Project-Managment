package com.aalperen.service;

import com.aalperen.entity.Chat;
import com.aalperen.entity.Message;
import com.aalperen.entity.User;
import com.aalperen.repository.MessageRepository;
import com.aalperen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content)  throws Exception{
        User sender = userRepository.findById(senderId).orElseThrow(()-> new RuntimeException("User not found"));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);

        List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());

        return messages;
    }
}
