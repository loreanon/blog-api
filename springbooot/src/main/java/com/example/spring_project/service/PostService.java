package com.example.spring_project.service;

import com.example.spring_project.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();
    Post getPostbyId(long id);
    Post savePost(Post post);
    Post updatePost(long id, Post post);
    void deletePost(long id);

}
