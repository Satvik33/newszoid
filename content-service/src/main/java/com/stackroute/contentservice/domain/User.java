package com.stackroute.contentservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable {

    @Id
    private String username;
    private String name;
    private String email;
    private Date dateOfBirth;

    private List<String> newsPreferences;
}
