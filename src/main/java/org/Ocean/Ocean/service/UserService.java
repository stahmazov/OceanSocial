package org.Ocean.Ocean.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.Ocean.Ocean.dto.request.UserRequest;
import org.Ocean.Ocean.dto.response.UserResponse;
import org.Ocean.Ocean.entity.User;
import org.Ocean.Ocean.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        User savedUser = userRepository.save(UserRequest.userRequestToUser(userRequest));
        return UserResponse.userToUserResponse(savedUser);
    }

    @Transactional
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(UserResponse::userToUserResponse).collect(Collectors.toList());
    }

    @Transactional
    public UserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("The user is not found"));
        return UserResponse.userToUserResponse(user);
    }

    @Transactional
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User is not found."));
        User requestUser = UserRequest.userRequestToUser(userRequest);
        User user = updateUser(foundUser, requestUser);
        User savedUser = userRepository.save(user);
        return UserResponse.userToUserResponse(savedUser);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    protected User updateUser(User user, User newUser){
        Optional.ofNullable(newUser.getName()).ifPresent(user::setName);
        Optional.ofNullable(newUser.getSurname()).ifPresent(user::setSurname);
        Optional.ofNullable(newUser.getBirthdate()).ifPresent(user::setBirthdate);
        return user;
    }

    public Boolean deleteUserByUsername(String username) {
        try {
            User foundedUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User is not found."));
            userRepository.delete(foundedUser);
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }
}

