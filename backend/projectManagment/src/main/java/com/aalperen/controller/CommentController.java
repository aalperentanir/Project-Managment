package com.aalperen.controller;

import com.aalperen.entity.Comment;
import com.aalperen.entity.User;
import com.aalperen.request.CreateCommentRequest;
import com.aalperen.response.MessageResponse;
import com.aalperen.service.CommentService;
import com.aalperen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest req,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);

        Comment createdComment = commentService.createComment(req.getContent(), req.getIssueId(), user.getId());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);

        commentService.deleteComment(commentId, user.getId());

        MessageResponse response = new MessageResponse();
        response.setMessage("Comment deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/comments/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception {
        List<Comment> comments = commentService.findCommentByIssueId(issueId);

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }
}
