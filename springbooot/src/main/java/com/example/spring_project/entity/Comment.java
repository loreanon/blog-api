package com.example.spring_project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "comments"
)
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String email;
    private String body;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "post_id",
            nullable = false
    )
    private Post post;
}
