package com.isep.lucky_data.controller;

import com.isep.lucky_data.configuration.CurrentUser;
import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.payload.request.UserRequest;
import com.isep.lucky_data.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @GetMapping
    @ApiOperation(value = "Gets the current user" , authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getCurrentUser(userPrincipal));
    }

    @Secured("ROLE_USER")
    @PutMapping
    @ApiOperation(value = "Updates the user infos" , authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<?> updateCurrentUser(@RequestBody UserRequest userRequest, @CurrentUser UserPrincipal userPrincipal) {
        userService.updateCurrentUser(userRequest, userPrincipal);
        return ResponseEntity.ok().build();
    }
}
