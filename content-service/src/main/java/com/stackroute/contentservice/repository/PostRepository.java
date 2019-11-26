package com.stackroute.contentservice.repository;

import com.stackroute.contentservice.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PostRepository extends MongoRepository<Post, BigInteger> {
}
