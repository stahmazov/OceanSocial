package org.Ocean.Ocean.controller;

import org.Ocean.Ocean.dto.request.PostRequest;
import org.Ocean.Ocean.dto.response.PostResponse;
import org.Ocean.Ocean.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/all")
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("{username}/all")
    public List<PostResponse> getAllByUsername(@PathVariable String username){
        return postService.getAllByUsername(username);
    }

    @PostMapping("/{username}/create")
    public PostResponse createPost(@PathVariable String username, @RequestBody PostRequest postRequest){
        return postService.createPost(username, postRequest);
    }

    @PutMapping("/{postId}/update")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        return postService.updatePost(postId, postRequest);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        Boolean status = postService.deletePostById(postId);
        if (status){
            return ResponseEntity.ok("The post is deleted successfully");
        } else{
            return ResponseEntity.badRequest().body("The post has not been deleted.");
        }

    }
}
