package com.aalperen.service;

import com.aalperen.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(String content, Long issueId, Long userId) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId) throws Exception;



}
