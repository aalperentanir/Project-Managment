package com.aalperen.controller;

import com.aalperen.entity.Subscription;
import com.aalperen.entity.User;
import com.aalperen.enums.SubType;
import com.aalperen.service.SubscriptionService;
import com.aalperen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);

        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestHeader("Authorization")String jwt,
                                                            @RequestParam SubType subType) throws Exception {
        User user = userService.findUserProfile(jwt);

        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), subType);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
