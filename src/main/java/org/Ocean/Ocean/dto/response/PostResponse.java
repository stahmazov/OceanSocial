package org.Ocean.Ocean.dto.response;

import org.Ocean.Ocean.entity.Post;
import org.Ocean.Ocean.entity.User;

import java.time.LocalDate;

public record PostResponse(String username,
                           String title,
                           String body,
                           LocalDate createdDate) {
    public static PostResponse postToPostResponse(Post post){
        return new PostResponse(post.getUser().getUsername(), post.getTitle(), post.getBody(),
                post.getCreatedDate());
    }
}
