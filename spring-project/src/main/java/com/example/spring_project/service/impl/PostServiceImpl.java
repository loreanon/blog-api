package com.example.spring_project.service.impl;

import com.example.spring_project.entity.Post;
import com.example.spring_project.exception.ResourceNotFound;
import com.example.spring_project.repository.PostRepository;
import com.example.spring_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostbyId(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("post", "postId", id));
        return post;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, Post post) {
        Post savedPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("post", "postId", id));
        savedPost.setTitle(post.getTitle());
        savedPost.setDescription(post.getDescription());
        savedPost.setContent(post.getContent());
        return postRepository.save(savedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("post", "postId", id));
        postRepository.delete(post);
    }
}
