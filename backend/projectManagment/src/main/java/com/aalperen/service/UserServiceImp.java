package com.aalperen.service;

import com.aalperen.config.JwtProvider;
import com.aalperen.entity.User;
import com.aalperen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserProfile(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);

        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isEmpty()){
            throw new Exception("User not found");
        }

        return opt.get();

    }

    @Override
    public User updateUsersProjectSize(User user, int number) throws Exception {

        user.setProjectSize(user.getProjectSize() + number);

        return userRepository.save(user);
    }
}
