package org.Ocean.Ocean.service;

import jakarta.transaction.Transactional;
import org.Ocean.Ocean.dto.request.CommentRequest;
import org.Ocean.Ocean.dto.response.CommentResponse;
import org.Ocean.Ocean.entity.Comment;
import org.Ocean.Ocean.entity.Post;
import org.Ocean.Ocean.entity.User;
import org.Ocean.Ocean.repository.CommentRepository;
import org.Ocean.Ocean.repository.PostRepository;
import org.Ocean.Ocean.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<CommentResponse> getAllCommentsByPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(("There is not a post with entered ID")));
        List<Comment> commentsByPost = commentRepository.findCommentsByPost(post);
        return commentsByPost.stream().map(CommentResponse::commentToCommentResponse).collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponse> getAllCommentsByUsername(String username) {
        User user = getUserByUsername(username);
        List<Comment> commentsByUser = commentRepository.findCommentsByUser(user);
        return commentsByUser.stream().map(CommentResponse::commentToCommentResponse).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse createComment(String username, Long postId, CommentRequest commentRequest) {
        User user = getUserByUsername(username);
        Post post = getPostByPostId(postId);
        Comment comment = CommentRequest.commentResponseToComment(commentRequest);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.commentToCommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse updateComment(String username, Long postId, Long commentId, CommentRequest commentRequest) {
        User user = getUserByUsername(username);
        Post post = getPostByPostId(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("the Comment is not found"));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            Optional.ofNullable(commentRequest.body()).ifPresent(comment::setBody);
            Comment savedComment = commentRepository.save(comment);
            return CommentResponse.commentToCommentResponse(savedComment);
        }
        return null;
    }

    @Transactional
    public boolean deleteComment(String username, Long postId, Long commentId) {
        User user = getUserByUsername(username);
        Post post = getPostByPostId(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("The comment is not found."));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("The user is not found"));
    }

    private Post getPostByPostId(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("The post is not found"));
    }
}
