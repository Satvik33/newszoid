package com.stackroute.recommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@SpringBootApplication
@EnableNeo4jRepositories("com.stackroute.recommendationservice")
public class SampleMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMovieApplication.class, args);
    }
}
