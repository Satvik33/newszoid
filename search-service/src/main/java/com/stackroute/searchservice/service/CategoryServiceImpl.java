package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Category;
import com.stackroute.searchservice.exceptions.CategoryNotFoundException;
import com.stackroute.searchservice.repository.CategoryRepository;
import com.stackroute.searchservice.exceptions.CategoryAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(String category) throws CategoryNotFoundException {
        Category categoryFind = categoryRepository.findCategoryByCategory(category);
        if(categoryFind == null) {
            throw new CategoryNotFoundException("Category with name "+category+" not found!");
        }
        return categoryFind;
    }

    @Override
    public Category addCategory(Category category) throws CategoryAlreadyExistsException {
        Category categoryFind = categoryRepository.findCategoryByCategory(category.getCategory());
        if(categoryFind != null) {
            throw new CategoryAlreadyExistsException("Category with name "+category+" already exists!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) throws CategoryNotFoundException {
        Category categoryFind = categoryRepository.findCategoryByCategory(category.getCategory());
        if(categoryFind == null) {
            throw new CategoryNotFoundException("Category with name "+category+" not found!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(String category) throws CategoryNotFoundException {
        Category categoryFind = categoryRepository.findCategoryByCategory(category);
        if(categoryFind == null) {
            throw new CategoryNotFoundException("Category with name "+category+" not found!");
        }
        categoryRepository.delete(categoryFind);
        return categoryFind;
    }
}
