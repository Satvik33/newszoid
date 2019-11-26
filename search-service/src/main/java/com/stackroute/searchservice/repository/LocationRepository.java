package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    public Location findLocationByLocation(String Location);
}
