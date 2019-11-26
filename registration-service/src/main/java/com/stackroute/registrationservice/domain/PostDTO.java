package com.stackroute.registrationservice.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PostDTO implements Serializable {
    private BigInteger id;
    private String title;
    private String videoUrl;
    private Date timestamp;
    private User postedBy;
    private List<String> tags;
    private String category;
    private String location;
    private List<String> likedBy;
    private List<String> flaggedBy;
    private List<String> watchedBy;
}
