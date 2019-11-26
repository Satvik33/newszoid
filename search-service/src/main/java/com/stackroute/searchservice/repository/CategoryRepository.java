package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    public Category findCategoryByCategory(String category);
}
