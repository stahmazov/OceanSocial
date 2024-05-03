package org.Ocean.Ocean.service;

import org.Ocean.Ocean.dto.request.PostRequest;
import org.Ocean.Ocean.dto.response.PostResponse;
import org.Ocean.Ocean.entity.Post;
import org.Ocean.Ocean.entity.User;
import org.Ocean.Ocean.repository.PostRepository;
import org.Ocean.Ocean.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponse> getAllPosts(){
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        return posts.stream().map(PostResponse::postToPostResponse).collect(Collectors.toList());
    }

    public List<PostResponse> getAllByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("The user is not found"));
        return postRepository.findAllByUser(user).stream().map(PostResponse::postToPostResponse).collect(Collectors.toList());
    }


    public PostResponse createPost(String username, PostRequest postRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("The user is not found"));
        Post post = PostRequest.postRequestToPost(postRequest);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return PostResponse.postToPostResponse(savedPost);
    }


    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("The post is not found."));
        Optional.ofNullable(postRequest.title()).ifPresent(foundPost::setTitle);
        Optional.ofNullable(postRequest.body()).ifPresent(foundPost::setBody);
        Post savedPost = postRepository.save(foundPost);
        return PostResponse.postToPostResponse(savedPost);

    }

    public Boolean deletePostById(Long postId) {
        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("The post is not found."));
            postRepository.deleteById(postId);
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
