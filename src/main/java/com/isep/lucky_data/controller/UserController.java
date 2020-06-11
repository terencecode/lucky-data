package com.isep.lucky_data.controller;

import com.isep.lucky_data.configuration.CurrentUser;
import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.converter.UserToUserResponseConverter;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.payload.request.EditRoleRequest;
import com.isep.lucky_data.payload.request.UserRequest;
import com.isep.lucky_data.payload.response.UserResponse;
import com.isep.lucky_data.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("/user")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping
    @ApiOperation(value = "Gets the current user" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        ApplicationUser user = userService.getCurrentUser(userPrincipal);
        UserToUserResponseConverter converter = new UserToUserResponseConverter();
        UserResponse userResponse = converter.convertFromEntity(user);
        return ResponseEntity.ok(userResponse);
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @PutMapping
    @ApiOperation(value = "Updates the user infos" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<?> updateCurrentUser(@RequestBody UserRequest userRequest, @CurrentUser UserPrincipal userPrincipal) {
        userService.updateCurrentUser(userRequest, userPrincipal);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    @ApiOperation(value = "Gets all users" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<Collection<UserResponse>> getAllUsers() {
        List<ApplicationUser> users = userService.getAllUsers();
        UserToUserResponseConverter converter = new UserToUserResponseConverter();
        Collection<UserResponse> responses = converter.createFromEntities(users);
        return ResponseEntity.ok(responses);
    }

    @Secured({"ROLE_ADMIN"})
    @Transactional
    @DeleteMapping("/delete/{email}")
    @ApiOperation(value = "Delete user" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @Transactional
    @PostMapping("/editRole")
    @ApiOperation(value = "Edit role to user" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<?> editUserRole(@RequestBody EditRoleRequest editRoleRequest) {
        Integer roleId = -1;
        switch (editRoleRequest.getRole()){
            case "ROLE_USER":  roleId = 1;
                break;
            case "ROLE_DATA_EXPERT":  roleId = 2;
                break;
            case "ROLE_ADMIN":  roleId = 3;
                break;
        }

        if (editRoleRequest.getaddRole()) {
            userService.AddUserRole(roleId, editRoleRequest.getEmail());
        } else {
            userService.DeleteUserRole(roleId, editRoleRequest.getEmail());
        }
        return ResponseEntity.ok().build();
    }
}
