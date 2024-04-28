package org.Ocean.Ocean.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @NotBlank(message = "comment text must not be empty")
    @Column(columnDefinition = "TEXT")
    private String body;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate createdDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

}
