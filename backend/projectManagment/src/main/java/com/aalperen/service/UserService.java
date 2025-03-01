package com.aalperen.service;

import com.aalperen.entity.User;

public interface UserService {

    User findUserProfile(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user, int number) throws Exception;



}
