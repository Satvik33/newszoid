package com.stackroute.registrationservice.repository;

import com.stackroute.registrationservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRepository extends MongoRepository<User, String> {
   public User findByUsername(String username);
}
