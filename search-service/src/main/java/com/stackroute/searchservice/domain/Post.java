package com.stackroute.searchservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    private BigInteger id;
    private String title;
    private String videoUrl;
    private Date timestamp;
    private User postedBy;
    private List<String> tags;
    private List<User> likedBy;
    private List<User> flaggedBy;
    private List<User> watchedBy;
}
