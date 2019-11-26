package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "PUBLISHED")
public class RelPublished {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User person;

    @EndNode
    private Post post;

    private Date publishedDate;

    public RelPublished() {
    }

    public RelPublished(Post post, User actor, Date publishedDate) {
        this.post = post;
        this.person = actor;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public User getPerson() {
        return person;
    }

    public Post getPost() {
        return post;
    }

    public Date getPublishedDate() {
        return this.publishedDate;
    }
}
