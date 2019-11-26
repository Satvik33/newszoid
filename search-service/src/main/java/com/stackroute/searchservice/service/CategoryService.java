package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Category;
import com.stackroute.searchservice.exceptions.CategoryAlreadyExistsException;
import com.stackroute.searchservice.exceptions.CategoryNotFoundException;

public interface CategoryService {
    Category getCategory(String category) throws CategoryNotFoundException;
    Category addCategory(Category category) throws CategoryAlreadyExistsException;
    Category updateCategory(Category category) throws CategoryNotFoundException;
    Category deleteCategory(String category) throws CategoryNotFoundException;
}
