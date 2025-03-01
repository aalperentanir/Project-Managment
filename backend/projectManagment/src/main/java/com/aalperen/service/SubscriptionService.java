package com.aalperen.service;

import com.aalperen.entity.Subscription;
import com.aalperen.entity.User;
import com.aalperen.enums.SubType;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId)throws Exception;

    Subscription upgradeSubscription(Long userId, SubType subType)throws Exception;

    boolean isValid(Subscription subscription);
}
