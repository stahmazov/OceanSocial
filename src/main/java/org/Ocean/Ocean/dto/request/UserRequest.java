package org.Ocean.Ocean.dto.request;

import org.Ocean.Ocean.entity.User;

import java.time.LocalDate;

public record UserRequest(String username,
                          String password,
                          String name,
                          String surname,
                          String email,
                          LocalDate birthdate) {
    public static User userRequestToUser(UserRequest userRequest){
        User user=new User();
        user.setUsername(userRequest.username);
        user.setName(userRequest.name);
        user.setSurname(userRequest.surname);
        user.setEmail(userRequest.email);
        user.setPassword(userRequest.password);
        user.setBirthdate(userRequest.birthdate);
        return user;
    }
}
