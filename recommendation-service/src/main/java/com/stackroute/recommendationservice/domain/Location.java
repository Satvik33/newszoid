package com.stackroute.recommendationservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.Set;

@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Location {
    @Id
    private String name;
    @Relationship(type = "LOCATED_IN", direction = Relationship.OUTGOING)
    private Location isLocatedIn;
    @Relationship(type = "LOCATED_IN", direction = Relationship.INCOMING)
    private Set<Location> placesUnder;
}
