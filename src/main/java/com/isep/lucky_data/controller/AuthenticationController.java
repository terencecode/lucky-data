package com.isep.lucky_data.controller;

import com.isep.lucky_data.payload.request.LoginRequest;
import com.isep.lucky_data.payload.request.SignUpRequest;
import com.isep.lucky_data.payload.response.ApiResponse;
import com.isep.lucky_data.payload.response.JwtAuthenticationResponse;
import com.isep.lucky_data.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new JwtAuthenticationResponse(authenticationService.authenticateUser(loginRequest)));
    }

    @PutMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = null;
        ApiResponse response = authenticationService.registerUser(signUpRequest, location);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(location).body(new ApiResponse(true, "ApplicationUser registered successfully"));
    }

    @GetMapping("/available")
    public ResponseEntity<?> checkEmailAvailability (@RequestParam String email) {
        boolean available = authenticationService.checkEmailAvailability(email);
        return available ? ResponseEntity.ok().body(available) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
