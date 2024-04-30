package org.Ocean.Ocean.dto.response;
import org.Ocean.Ocean.dto.request.UserRequest;
import org.Ocean.Ocean.entity.User;

import java.time.LocalDate;

public record UserResponse(String username,
                           String name,
                           String surname,
                           String email,
                           LocalDate birthdate) {

    public static UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getBirthdate());
    }

}
