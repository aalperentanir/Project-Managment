package com.aalperen.controller;

import com.aalperen.entity.Issue;
import com.aalperen.entity.User;
import com.aalperen.request.IssueDto;
import com.aalperen.request.IssueRequest;
import com.aalperen.response.MessageResponse;
import com.aalperen.service.IssueService;
import com.aalperen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        Issue issue = issueService.getIssueById(issueId);
        return new ResponseEntity<>(issue, HttpStatus.OK);

    }

    @PostMapping("/issue")
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest req,
                                                 @RequestHeader("Authorization")String token) throws Exception {

        User userToken = userService.findUserProfile(token);
        User user = userService.findUserById(userToken.getId());

            Issue createdIssue = issueService.createIssue(req, userToken);

            IssueDto issueDto = new IssueDto();
            issueDto.setId(createdIssue.getId());
            issueDto.setTitle(createdIssue.getTitle());
            issueDto.setDescription(createdIssue.getDescription());
            issueDto.setProject(createdIssue.getProject());
            issueDto.setStatus(createdIssue.getStatus());
            issueDto.setProjectID(createdIssue.getProjectID());
            issueDto.setPriority(createdIssue.getPriority());
            issueDto.setDueDate(createdIssue.getDueDate());
            issueDto.setAssigned(createdIssue.getAssign());
            issueDto.setTags(createdIssue.getTags());

            return new ResponseEntity<>(issueDto, HttpStatus.OK);



    }


    @DeleteMapping("/issue/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId, @RequestHeader("Authorization")String token) throws Exception {
        User user = userService.findUserProfile(token);
         issueService.deleteIssue(issueId,user.getId());

         MessageResponse response = new MessageResponse();
         response.setMessage("Issue deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/issue/{issueId}/assigned/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return new ResponseEntity<>(issue, HttpStatus.OK);

    }


    @GetMapping("/issue/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        List<Issue> issues = issueService.getIssueByProjectId(projectId);
        return new ResponseEntity<>(issues, HttpStatus.OK);

    }


    @PutMapping("/issue/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception {
        Issue issue = issueService.updateStatus(issueId,status);
        return new ResponseEntity<>(issue, HttpStatus.OK);

    }
}
