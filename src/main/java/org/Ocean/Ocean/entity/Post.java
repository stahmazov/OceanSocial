package org.Ocean.Ocean.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;

    @NotBlank(message = "Post text must not be empty")
    private String title;

    @NotBlank(message = "Post body must not be empty")
    @Column(columnDefinition = "TEXT")
    private String body;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate createdDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

}
