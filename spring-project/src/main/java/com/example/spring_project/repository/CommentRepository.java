package com.example.spring_project.repository;

import com.example.spring_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByPostId(Long postId);
}
