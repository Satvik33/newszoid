package com.stackroute.searchservice.controller;

import com.stackroute.searchservice.domain.Category;
import com.stackroute.searchservice.domain.Location;
import com.stackroute.searchservice.exceptions.CategoryNotFoundException;
import com.stackroute.searchservice.exceptions.LocationNotFoundException;
import com.stackroute.searchservice.service.CategoryService;
import com.stackroute.searchservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Repository
@RequestMapping("api/v1")
public class SearchController {
    private CategoryService categoryService;
    private LocationService locationService;

    @Autowired
    public SearchController(CategoryService categoryService, LocationService locationService) {
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getCategory(@PathVariable String category) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Category>(categoryService.getCategory(category), HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping("location/{location}")
    public ResponseEntity<?> getLocation(@PathVariable String location) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Location>(locationService.getLocation(location), HttpStatus.OK);
        } catch (LocationNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
