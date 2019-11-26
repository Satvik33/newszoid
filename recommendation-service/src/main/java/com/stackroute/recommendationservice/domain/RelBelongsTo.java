package com.stackroute.recommendationservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "BELONGS_TO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RelBelongsTo {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Post post;
    @EndNode
    private SubCategory subCategory;
}
