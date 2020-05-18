package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.model.Role;
import com.isep.lucky_data.payload.response.UserResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class UserToUserResponseConverter extends OneWayConverter<UserResponse, ApplicationUser> {
    public UserToUserResponseConverter() {
        super(user -> {
            if (user == null) {
                return null;
            }

            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String department = user.getDepartment().getName();
            Set<Role> roles = user.getRoles();
            List<String> roleList = new ArrayList<>();
            for (Role r : roles){
                roleList.add(r.getRole().getName());
            }

            return new UserResponse(firstName, lastName, email, department, roleList);
        });
    }
}
