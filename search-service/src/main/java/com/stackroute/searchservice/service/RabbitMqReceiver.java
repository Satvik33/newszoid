package com.stackroute.searchservice.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.searchservice.domain.Category;
import com.stackroute.searchservice.domain.Location;
import com.stackroute.searchservice.domain.Post;
import com.stackroute.searchservice.domain.PostDTO;
import com.stackroute.searchservice.exceptions.CategoryAlreadyExistsException;
import com.stackroute.searchservice.exceptions.CategoryNotFoundException;
import com.stackroute.searchservice.exceptions.LocationAlreadyExistsException;
import com.stackroute.searchservice.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class RabbitMqReceiver {
    private LocationService locationService;
    private CategoryService categoryService;

    @Autowired
    public RabbitMqReceiver(LocationService locationService, CategoryService categoryService) {
        this.locationService = locationService;
        this.categoryService = categoryService;
    }

    public void receiveMessage(String message) {
        System.out.println("in receiver");
        try {
            PostDTO postDTO = new ObjectMapper().readValue(message, PostDTO.class);
            Post post = Post.builder()
                            .id(postDTO.getId())
                            .title(postDTO.getTitle())
                            .timestamp(postDTO.getTimestamp())
                            .tags(postDTO.getTags())
                            .videoUrl(postDTO.getVideoUrl())
                            .postedBy(postDTO.getPostedBy())
                            .likedBy(postDTO.getLikedBy())
                            .flaggedBy(postDTO.getFlaggedBy())
                            .watchedBy(postDTO.getWatchedBy())
                            .build();
            try {
                Location location = locationService.getLocation(postDTO.getLocation());
                List<Post> posts = location.getPosts();
                Iterator itr = posts.iterator();
                while(itr.hasNext()) {
                    Post postFind = (Post) itr.next();
                    if(postFind.getId().equals(post.getId())) {
                        System.out.printf("found!");
                        itr.remove();
                    }
                }
                posts.add(post);
                location.setPosts(posts);
                locationService.updateLocation(location);
            } catch (LocationNotFoundException e) {
                List<Post> posts = new ArrayList<Post>();
                posts.add(post);
                Location location = Location.builder()
                                            .location(postDTO.getLocation())
                                            .posts(posts)
                                            .build();
                try {
                    locationService.addLocation(location);
                } catch (LocationAlreadyExistsException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                Category category = categoryService.getCategory(postDTO.getCategory());
                List<Post> posts = category.getPosts();
                Iterator itr = posts.iterator();
                while(itr.hasNext()) {
                    Post postFind = (Post) itr.next();
                    if(postFind.getId().equals(post.getId())) {
                        System.out.printf("found!");
                        itr.remove();
                    }
                }
                posts.add(post);
                category.setPosts(posts);
                categoryService.updateCategory(category);
            } catch (CategoryNotFoundException e) {
                List<Post> posts = new ArrayList<Post>();
                posts.add(post);
                Category category = Category.builder()
                        .category(postDTO.getCategory())
                        .posts(posts)
                        .build();
                try {
                    categoryService.addCategory(category);
                } catch (CategoryAlreadyExistsException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
