package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.User;
import com.isep.lucky_data.payload.response.UserResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

public class UserToUserResponseConverter extends OneWayConverter<UserResponse, User> {
    public UserToUserResponseConverter() {
        super(user -> {
            if (user == null) {
                return null;
            }

            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();

            return new UserResponse(firstName, lastName, email);
        });
    }
}
