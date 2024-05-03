package org.Ocean.Ocean.dto.response;

import org.Ocean.Ocean.entity.Comment;

import java.time.LocalDate;

public record CommentResponse(String username,
                              Long postId,
                              String body,
                              LocalDate createdDate
                              ) {
    public static CommentResponse commentToCommentResponse(Comment comment){
        return new CommentResponse(comment.getUser().getUsername(),
                comment.getPost().getPostID(), comment.getBody(), comment.getCreatedDate());
    }
}
