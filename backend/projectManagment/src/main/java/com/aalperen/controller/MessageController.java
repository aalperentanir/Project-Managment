package com.aalperen.controller;

import com.aalperen.entity.Chat;
import com.aalperen.entity.Message;
import com.aalperen.entity.User;
import com.aalperen.request.CreateMessageRequest;
import com.aalperen.service.MessageService;
import com.aalperen.service.ProjectService;
import com.aalperen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

@PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest req) throws Exception {
        User user = userService.findUserById(req.getSenderId());

        Chat chat = projectService.getProjectById(req.getProjectId()).getChat();

        if (chat == null) {
            throw new Exception("Chat not found");
        }

        Message sendMessage = messageService.sendMessage(req.getSenderId(), req.getProjectId(), req.getContent());

        return new ResponseEntity<>(sendMessage, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
    List<Message> messages = messageService.getMessageByProjectId(projectId);

    return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
