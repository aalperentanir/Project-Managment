package com.aalperen.service;

import com.aalperen.entity.Issue;
import com.aalperen.entity.Project;
import com.aalperen.entity.User;
import com.aalperen.repository.IssueRepository;
import com.aalperen.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImp implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;



    @Override
    public Issue getIssueById(Long id) throws Exception {

        Optional<Issue> issue = issueRepository.findById(id);

        if (issue.isPresent()) {
            return issue.get();
        }
        throw new Exception("Issue not found");
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest,User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectID());

        Issue issue =  new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setProject(project);
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectID(issueRequest.getProjectID());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());

        return issueRepository.save(issue);
    }

    @Override
    public Optional<Issue> updateIssue(IssueRequest updatedIssue, Long userId, Long issueId) throws Exception {
        return Optional.empty();
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {

        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }


    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssign(user);

        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {

        Issue issue = getIssueById(issueId);
        issue.setStatus(status);

        return  issueRepository.save(issue);
    }
}
