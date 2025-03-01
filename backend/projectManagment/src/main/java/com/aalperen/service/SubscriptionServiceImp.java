package com.aalperen.service;

import com.aalperen.entity.Subscription;
import com.aalperen.entity.User;
import com.aalperen.enums.SubType;
import com.aalperen.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;


    @Override
    public Subscription createSubscription(User user) {

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartedDate(LocalDate.now());
        subscription.setEndedDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setSubType(SubType.FREE);


        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscription(Long userId) throws Exception {
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if (!isValid(subscription)){
            subscription.setSubType(SubType.FREE);
            subscription.setEndedDate(LocalDate.now().plusMonths(12));
            subscription.setStartedDate(LocalDate.now());
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, SubType subType) throws Exception {
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        subscription.setSubType(subType);
        subscription.setStartedDate(LocalDate.now());

        if (subType.equals(SubType.ANNUALLY)){
            subscription.setEndedDate(LocalDate.now().plusMonths(12));
        }else{
            subscription.setEndedDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {

        if (subscription.getSubType().equals(SubType.FREE)){
            return true;
        }

        LocalDate endDate = subscription.getEndedDate();
        LocalDate currentDate = LocalDate.now();

        return endDate.isBefore(currentDate) || endDate.isEqual(currentDate);
    }
}
