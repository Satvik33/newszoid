package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.domain.User;
import com.stackroute.registrationservice.exception.UserAlreadyExistsException;
import com.stackroute.registrationservice.exception.UserNotFoundException;

import java.util.List;

public interface UserRegistrationService {
    User saveUser(User user)throws UserAlreadyExistsException;
    User getUser(String username) throws UserNotFoundException;
    User updateUser(User user) throws UserNotFoundException;
}
