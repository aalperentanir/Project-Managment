package com.aalperen.service;

import com.aalperen.entity.Comment;
import com.aalperen.entity.Issue;
import com.aalperen.entity.User;
import com.aalperen.repository.CommentRepository;
import com.aalperen.repository.IssueRepository;
import com.aalperen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;


/*    @Override
    public Comment createComment(String content, Long issueId, Long userId) throws Exception {

        Optional<Issue> issueOptional = issueRepository.findById(issueId);

        Optional<User> userOptional = userRepository.findById(userId);


        if(issueOptional.isEmpty()) {
            throw new Exception("Issue not found"+ issueId);
        }

        if (userOptional.isEmpty()) {
            throw new Exception("User not found"+ userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;
    }*/

    @Override
    public Comment createComment(String content, Long issueId, Long userId) throws Exception {
        System.out.println("Creating comment for issueId: " + issueId + ", userId: " + userId);

        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        System.out.println("Issue found: " + issueOptional.isPresent());

        Optional<User> userOptional = userRepository.findById(userId);
        System.out.println("User found: " + userOptional.isPresent());

        if (issueOptional.isEmpty()) {
            throw new Exception("Issue not found for id: " + issueId);
        }

        if (userOptional.isEmpty()) {
            throw new Exception("User not found for id: " + userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()) {
            throw new Exception("Comment not found");
        }

        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }else{
            throw new Exception("Comment not deleted");
        }

    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) throws Exception {
        return commentRepository.findByIssueId(issueId);
    }

}
