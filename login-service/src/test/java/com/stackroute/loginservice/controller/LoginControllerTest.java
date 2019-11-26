package com.stackroute.loginservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.loginservice.config.AuthEntryPoint;
import com.stackroute.loginservice.config.JwtTokenUtil;
import com.stackroute.loginservice.domain.UserDTO;
import com.stackroute.loginservice.service.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private UserDetails userDetails;
    private UserDTO userDTO;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
//    @MockBean
//    private AuthEntryPoint authEntryPoint;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .build();
        userDTO = new UserDTO("username", "password");
        userDetails = new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(),
                new ArrayList<>());
    }

    @Test
    public void testAuthenticateSuccess() throws Exception {
        when(userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(userDetails);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
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
