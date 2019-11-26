package com.stackroute.registrationservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    private String username;
    private String name;
    private String email;
    private Date dateOfBirth;
    private List<String> newsPreferences;

    private List<Post> posts;
    private List<Post> liked;
    private List<Post> watched;
    private List<Post> flagged;
}
