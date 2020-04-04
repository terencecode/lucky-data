package com.isep.lucky_data.service;

import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.converter.UserToUserResponseConverter;
import com.isep.lucky_data.exception.UserNotFoundException;
import com.isep.lucky_data.model.User;
import com.isep.lucky_data.payload.request.UserRequest;
import com.isep.lucky_data.payload.response.UserResponse;
import com.isep.lucky_data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getCurrentUser(UserPrincipal userPrincipal) {

        Optional<User> user = userRepository.findById(userPrincipal.getId());

        if (user.isPresent()) {
            return new UserToUserResponseConverter().convertFromEntity(user.get());
        } else throw new UserNotFoundException();
    }

    public void updateCurrentUser(UserRequest userRequest, UserPrincipal userPrincipal) {
        Optional<User> user = userRepository.findById(userPrincipal.getId());

        if (user.isPresent()) {
            User updatedUser = user.get();
            updateCurrentUser(userRequest, updatedUser);
            userRepository.save(updatedUser);
        } else throw new UserNotFoundException();
    }

    private void updateCurrentUser(UserRequest userRequest, User user) {
        user.setFirstName(userRequest.getFirstName() == null ? user.getFirstName() : userRequest.getFirstName());
        user.setLastName(userRequest.getLastName() == null ? user.getLastName() : userRequest.getLastName());
        user.setEmail(userRequest.getEmail() == null ? user.getEmail() : userRequest.getEmail());
        user.setPassword(userRequest.getPassword() == null ? user.getPassword() : userRequest.getPassword());
    }
}
