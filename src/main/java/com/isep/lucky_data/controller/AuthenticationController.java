package com.isep.lucky_data.controller;

import com.isep.lucky_data.configuration.JwtTokenProvider;
import com.isep.lucky_data.exception.UserNotFoundException;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.payload.request.LoginRequest;
import com.isep.lucky_data.payload.request.SignUpRequest;
import com.isep.lucky_data.payload.response.ApiResponse;
import com.isep.lucky_data.payload.response.JwtAuthenticationResponse;
import com.isep.lucky_data.repository.ApplicationUserRepository;
import com.isep.lucky_data.service.AuthenticationService;
import com.isep.lucky_data.service.EmailService;
import com.isep.lucky_data.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authentication API")
public class AuthenticationController {

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticateUser(loginRequest);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token, tokenProvider.getJwtExpirationDateInMs());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = null;
        ApiResponse response = authenticationService.registerUser(signUpRequest, location);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(location).body(new ApiResponse(true, "ApplicationUser registered successfully"));
    }

    @GetMapping("/available")
    public ResponseEntity<?> checkEmailAvailability(@RequestParam String email) {
        boolean available = authenticationService.checkEmailAvailability(email);
        return available ? ResponseEntity.ok().body(available) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/resetPasswordEmail/{email}")
    public ResponseEntity<?> resetPasswordEmail(@PathVariable("email") String email) {
        ApplicationUser user;
        try {
            user = userService.findByEmail(email);
            String newPassword = authenticationService.setRandomPassword(user);
            emailService.sendNewPassword(user, newPassword);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (MessagingException|IOException e) {
            return ResponseEntity.created(null).body(new ApiResponse(false, e.toString()));
        }

        return ResponseEntity.ok().build();
    }
}
