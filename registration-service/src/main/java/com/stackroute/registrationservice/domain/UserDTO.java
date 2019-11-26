package com.stackroute.registrationservice.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private String username;
    private String password;
}
