package com.stackroute.recommendationservice.repositories;

import com.stackroute.recommendationservice.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByUsername(String name);

    @Query("match (u:User) where n.videoID=1 match (u:User)-[:Viewed]->(n) return u")
    Collection<User> graph(@Param("videoID") int videoId);

}
