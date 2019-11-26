package com.stackroute.searchservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    private String username;
    private String name;
    private String email;
    private Date dateOfBirth;

    private List<String> newsPreferences;
}
