package com.aalperen.controller;

import com.aalperen.entity.Chat;
import com.aalperen.entity.Invitation;
import com.aalperen.entity.Project;
import com.aalperen.entity.User;
import com.aalperen.request.InviteRequest;
import com.aalperen.response.MessageResponse;
import com.aalperen.service.InvitationService;
import com.aalperen.service.ProjectService;
import com.aalperen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
                                                     @RequestParam(required = false) String tag,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        List<Project> projects = projectService.getProjectByTeam(user,category,tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        Project project = projectService.getProjectById(projectId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project,
                                                  @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        Project createdProject = projectService.createProject(project,user);

        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
                                                 @RequestBody Project project,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        Project updatedProject = projectService.updateProject(project,projectId);

        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long projectId,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        projectService.deleteProject(projectId, user.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Project deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/projects/search")
    public ResponseEntity<List<Project>> searchProject(@RequestParam(required = false) String keyword,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        List<Project> projects = projectService.searchProject(keyword,user);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/projects/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(@PathVariable Long projectId,
                                                  @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/projects/invite")
    public ResponseEntity<MessageResponse> inviteProject(@RequestBody InviteRequest req,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Invitation sent successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/projects/acceptInvitation")
    public ResponseEntity<Invitation> acceptInviteProject(@RequestParam String token,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfile(jwt);
        Invitation invitation = invitationService.acceptInvitation(token,user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());

        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }


}
