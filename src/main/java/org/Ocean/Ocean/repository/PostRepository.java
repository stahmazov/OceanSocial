package org.Ocean.Ocean.repository;

import org.Ocean.Ocean.entity.Post;
import org.Ocean.Ocean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);

    Optional<Post> findByUser(User user);

}
