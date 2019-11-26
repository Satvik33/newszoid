package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.domain.User;
import com.stackroute.registrationservice.exception.UserAlreadyExistsException;
import com.stackroute.registrationservice.exception.UserNotFoundException;
import com.stackroute.registrationservice.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository=userRegistrationRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRegistrationRepository.findByUsername(user.getUsername())!=null) {
            throw new UserAlreadyExistsException("You have already registered yourself!");
        }

        User savedUser = userRegistrationRepository.save(user);
        return savedUser;
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        User user = userRegistrationRepository.findByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }

        return user;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        User findUser = userRegistrationRepository.findByUsername(user.getUsername());
        if(findUser == null) {
            throw new UserNotFoundException("User not found!");
        }

        return userRegistrationRepository.save(user);
    }
}
