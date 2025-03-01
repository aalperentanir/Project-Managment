package com.aalperen.service;

import com.aalperen.entity.Issue;
import com.aalperen.entity.User;
import com.aalperen.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long id) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issueRequest,User user) throws Exception;

    Optional<Issue> updateIssue(IssueRequest updatedIssue,Long userId, Long issueId) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;


    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
