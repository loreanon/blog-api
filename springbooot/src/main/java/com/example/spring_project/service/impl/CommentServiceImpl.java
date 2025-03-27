package com.example.spring_project.service.impl;

import com.example.spring_project.entity.Comment;
import com.example.spring_project.entity.Post;
import com.example.spring_project.exception.BlogApiException;
import com.example.spring_project.exception.ResourceNotFound;
import com.example.spring_project.repository.CommentRepository;
import com.example.spring_project.repository.PostRepository;
import com.example.spring_project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long postId, Comment newComment) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        // set post to comment
        newComment.setPost(post);
        // save comment
        return commentRepository.save(newComment);
    }


    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        return comment;
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return (List<Comment>) commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment updateComment) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        // retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        comment.setName(updateComment.getName());
        comment.setEmail(updateComment.getEmail());
        comment.setBody(updateComment.getBody());
        return commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        // retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }
}

