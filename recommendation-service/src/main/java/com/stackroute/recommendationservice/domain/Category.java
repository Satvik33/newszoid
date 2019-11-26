package com.stackroute.recommendationservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category {
    @Id
    private String name;

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    private Set<SubCategory> subCategories;
}
