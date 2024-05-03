package org.Ocean.Ocean.controller;

import org.Ocean.Ocean.dto.request.CommentRequest;
import org.Ocean.Ocean.dto.response.CommentResponse;
import org.Ocean.Ocean.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService){
        this.commentService = commentService;
    }


    @GetMapping("/{postId}/comments")
    public List<CommentResponse> getAllCommentsByPost(@PathVariable Long postId){
        return commentService.getAllCommentsByPost(postId);
    }

    @GetMapping("/{username}/comments")
    public List<CommentResponse> getAllCommentsByUsername(@PathVariable String username){
        return commentService.getAllCommentsByUsername(username);
    }

    @PostMapping("/{username}/{postId}/comment")
    public CommentResponse createComment(@PathVariable String username, @PathVariable Long postId, @RequestBody CommentRequest commentRequest) {
        return commentService.createComment(username, postId, commentRequest);
    }

    @PutMapping("/{username}/{postId}/{commentId}")
    public CommentResponse updateComment(@PathVariable String username, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(username, postId, commentId, commentRequest);
    }

    @DeleteMapping("/{username}/{postId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String username, @PathVariable Long postId, @PathVariable Long commentId) {
        if (commentService.deleteComment(username, postId, commentId)) {
            return ResponseEntity.ok("Comment deleted");
        } else {
            return ResponseEntity.ok("Comment not deleted");
        }
    }
}
