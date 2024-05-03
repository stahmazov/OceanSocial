package org.Ocean.Ocean.dto.request;

import org.Ocean.Ocean.entity.Comment;

import java.time.LocalDateTime;

public record CommentRequest(String body) {
    public static Comment commentResponseToComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setBody(commentRequest.body);
        comment.setCreatedDate(LocalDateTime.now());
        return comment;
    }
}
