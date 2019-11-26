package com.stackroute.contentservice.service;

import com.stackroute.contentservice.domain.Post;
import com.stackroute.contentservice.exception.PostAlreadyExistsException;
import com.stackroute.contentservice.exception.PostNotFoundException;

import java.math.BigInteger;
import java.util.List;

public interface PostService {
    Post getPost(BigInteger id) throws PostNotFoundException;
    Post savePost(Post post) throws PostAlreadyExistsException;
    Post updatePost(Post post) throws PostNotFoundException;
    Post deletePost(BigInteger id) throws PostNotFoundException;
    List<Post> getAllPosts() throws PostNotFoundException;
    List<Post> getPostsByCategory(String category) throws PostNotFoundException;
    List<Post> getTrendingPosts(List<Post> posts)throws PostNotFoundException;
   // List<Post> getNumberOfPostsByCategory(List<Post> posts)throws PostNotFoundException;
   // List<Post> getTrendingPostsByCategory(List<Post> posts)throws PostNotFoundException;
}
