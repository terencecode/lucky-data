package com.isep.lucky_data.service;

import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.converter.UserToUserResponseConverter;
import com.isep.lucky_data.exception.UserNotFoundException;
import com.isep.lucky_data.model.ApplicationUser;
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

    public ApplicationUser getCurrentUser(UserPrincipal userPrincipal) {

        Optional<ApplicationUser> user = userRepository.findById(userPrincipal.getId());

        if (user.isPresent()) {
            return user.get();
        } else throw new UserNotFoundException();
    }

    public void updateCurrentUser(UserRequest userRequest, UserPrincipal userPrincipal) {
        Optional<ApplicationUser> user = userRepository.findById(userPrincipal.getId());

        if (user.isPresent()) {
            ApplicationUser updatedApplicationUser = user.get();
            updateCurrentUser(userRequest, updatedApplicationUser);
            userRepository.save(updatedApplicationUser);
        } else throw new UserNotFoundException();
    }

    private void updateCurrentUser(UserRequest userRequest, ApplicationUser applicationUser) {
        applicationUser.setFirstName(userRequest.getFirstName() == null ? applicationUser.getFirstName() : userRequest.getFirstName());
        applicationUser.setLastName(userRequest.getLastName() == null ? applicationUser.getLastName() : userRequest.getLastName());
        applicationUser.setEmail(userRequest.getEmail() == null ? applicationUser.getEmail() : userRequest.getEmail());
        applicationUser.setPassword(userRequest.getPassword() == null ? applicationUser.getPassword() : userRequest.getPassword());
    }
}
