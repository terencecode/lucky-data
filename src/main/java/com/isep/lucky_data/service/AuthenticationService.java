package com.isep.lucky_data.service;

import com.isep.lucky_data.configuration.JwtTokenProvider;
import com.isep.lucky_data.exception.AppException;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.model.Department;
import com.isep.lucky_data.model.Role;
import com.isep.lucky_data.model.RoleName;
import com.isep.lucky_data.payload.request.LoginRequest;
import com.isep.lucky_data.payload.request.SignUpRequest;
import com.isep.lucky_data.payload.response.ApiResponse;
import com.isep.lucky_data.repository.DepartmentRepository;
import com.isep.lucky_data.repository.RoleRepository;
import com.isep.lucky_data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Set;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public ApiResponse registerUser(SignUpRequest signUpRequest, URI location) {

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ApiResponse(false, "Email Address already in use!");
        }

        long userId;

        userId = registerUser(signUpRequest);


        location = (fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(userId).toUri());

        return new ApiResponse(true, "ApplicationUser registered successfully");
    }

    private long registerUser(SignUpRequest signUpRequest) {

        Department requestDepartment = departmentRepository.findByName(signUpRequest.getDepartmentName()).orElseThrow(
                () -> new AppException("Department " + signUpRequest.getDepartmentName() + " does not exists !"));

        ApplicationUser applicationUser = new ApplicationUser(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), requestDepartment);

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));

        addRole(applicationUser, RoleName.fromName(signUpRequest.getRoleName()));
        ApplicationUser result = userRepository.save(applicationUser);
        userRepository.flush();
        return result.getId();
    }

    private void addRole(ApplicationUser applicationUser, RoleName roleName) {
        Role userRole = roleRepository.findByRole(roleName)
                .orElseThrow(() -> new AppException("User Role not set."));
        Set<Role> roles = applicationUser.getRoles();
        roles.add(userRole);
        applicationUser.setRoles(roles);
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }

    public boolean checkEmailAvailability (String email) {
        return !userRepository.findByEmail(email).isPresent();
    }
}