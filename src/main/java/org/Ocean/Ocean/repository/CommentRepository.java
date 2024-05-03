package org.Ocean.Ocean.repository;

import org.Ocean.Ocean.entity.Comment;
import org.Ocean.Ocean.entity.Post;
import org.Ocean.Ocean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost(Post post);
    List<Comment> findCommentsByUser(User user);
}
