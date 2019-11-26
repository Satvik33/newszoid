package com.stackroute.registrationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.registrationservice.domain.User;
import com.stackroute.registrationservice.domain.UserDAO;
import com.stackroute.registrationservice.exception.UserAlreadyExistsException;
import com.stackroute.registrationservice.service.UserRegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private User user;
    private UserDAO userDAO;

    @Mock
    private UserRegistrationService userRegistrationService;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Queue queue;
    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
        user = User.builder()
                    .username("username")
                    .build();
        userDAO = UserDAO.builder()
                    .username("username")
                    .password("password")
                    .build();
    }

    @Test
    public void getUser() throws Exception {
        when(userRegistrationService.getUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/register/"+userDAO.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        when(userRegistrationService.updateUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/register/"+userDAO.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
