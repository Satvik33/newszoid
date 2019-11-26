package com.stackroute.loginservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.loginservice.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMqReceiverService {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public void receiveMessage(String message) {
        UserDTO user = null;
        try {
            user = new ObjectMapper().readValue(message, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDetailsServiceImpl.save(user);
    }

}
