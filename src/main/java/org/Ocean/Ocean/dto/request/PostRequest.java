package org.Ocean.Ocean.dto.request;

import org.Ocean.Ocean.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostRequest(String title,
                          String body) {
    public static Post postRequestToPost(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.title);
        post.setBody(postRequest.body);
        post.setCreatedDate(LocalDate.now());
        return post;
    }
}
