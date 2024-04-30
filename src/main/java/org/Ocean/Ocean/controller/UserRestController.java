package org.Ocean.Ocean.controller;

import lombok.RequiredArgsConstructor;
import org.Ocean.Ocean.dto.request.UserRequest;
import org.Ocean.Ocean.dto.response.UserResponse;
import org.Ocean.Ocean.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserResponse getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{username}")
    public UserResponse updateUser(@PathVariable String username, UserRequest userRequest){
        return userService.updateUserByUsername(username, userRequest);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
        if (userService.deleteUserByUsername(username)){
            return ResponseEntity.ok("The user is deleted successfully");
        } else{
            return ResponseEntity.badRequest().body("The user is not founded or deleted.");
        }
    }
}
