package com.stackroute.loginservice.service;

import com.stackroute.loginservice.domain.DAOUser;
import com.stackroute.loginservice.domain.UserDTO;
import com.stackroute.loginservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {
    private DAOUser daoUser;
    private UserDTO userDTO;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder bCryptEncoder;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        daoUser = new DAOUser(1, "username", "password");
        userDTO = new UserDTO("username", "password");
    }

    @Test
    public void testLoadUserByUsernameSuccess() throws UsernameNotFoundException {
        when(userRepository.findByUsername(any())).thenReturn(daoUser);
        userDetailsServiceImpl.loadUserByUsername("username");
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameFailure() throws UsernameNotFoundException {
        when(userRepository.findByUsername(any())).thenReturn(null);
        userDetailsServiceImpl.loadUserByUsername("username");
    }
}
