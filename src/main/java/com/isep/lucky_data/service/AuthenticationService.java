package com.isep.lucky_data.service;

import com.isep.lucky_data.configuration.JwtTokenProvider;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.model.Department;
import com.isep.lucky_data.model.Role;
import com.isep.lucky_data.model.RoleName;
import com.isep.lucky_data.payload.request.LoginRequest;
import com.isep.lucky_data.payload.request.SignUpRequest;
import com.isep.lucky_data.payload.response.ApiResponse;
import com.isep.lucky_data.repository.DepartmentRepository;
import com.isep.lucky_data.repository.RoleRepository;
import com.isep.lucky_data.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public ApiResponse registerUser(SignUpRequest signUpRequest, URI location) {

        if(checkUserExists(signUpRequest.getEmail())) {
            return new ApiResponse(false, "Email Address already in use!");
        }

        Long userId;
        userId = registerUser(signUpRequest);
        location = (fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(userId).toUri());

        return new ApiResponse(true, "ApplicationUser registered successfully");
    }

    private Long registerUser(SignUpRequest signUpRequest) {

        Department requestDepartment = departmentRepository.findByName(signUpRequest.getDepartmentName()).orElseGet(
                () -> departmentRepository.save(new Department(signUpRequest.getDepartmentName())));

        ApplicationUser applicationUser = new ApplicationUser(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), requestDepartment);

        applicationUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        addRole(applicationUser);
        ApplicationUser savedUser = applicationUserRepository.save(applicationUser);
        Set<ApplicationUser> departmentUsers = requestDepartment.getApplicationUsers();
        if(departmentUsers == null) {
            departmentUsers = new HashSet<>();
            departmentUsers.add(savedUser);
            requestDepartment.setApplicationUsers(departmentUsers);
        }
        departmentRepository.save(requestDepartment);
        applicationUserRepository.flush();
        return savedUser.getId();
    }

    private void addRole(ApplicationUser applicationUser) {
        Role userRole = roleRepository.findByRole(RoleName.ROLE_USER);
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
        return !applicationUserRepository.findByEmail(email).isPresent();
    }

    public boolean checkUserExists(String email) {
        return applicationUserRepository.existsByEmail(email);
    }

    public String setRandomPassword(ApplicationUser user) {
        String newPassword = generatePassword(12);
        user.setPassword(passwordEncoder.encode(newPassword));
        applicationUserRepository.save(user);
        return newPassword;
    }

    private String generatePassword (int length) {
        if (length < 4) { length = 12; }

        final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();
        final char[] symbols = "^$?!@#%&".toCharArray();
        final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();

        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length-4; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
        password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
        password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
        password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
        password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);
        return password.toString();
    }

}
