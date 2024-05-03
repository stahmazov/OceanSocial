package org.Ocean.Ocean.dto.response;

import org.Ocean.Ocean.entity.Post;

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
