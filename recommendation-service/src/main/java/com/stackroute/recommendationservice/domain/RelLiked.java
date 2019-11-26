package com.stackroute.recommendationservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "LIKED")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RelLiked {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private User person;
    @EndNode
    private Post news;
    private Date date;
}
