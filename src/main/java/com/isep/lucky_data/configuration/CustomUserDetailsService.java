package com.isep.lucky_data.configuration;

import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        ApplicationUser applicationUser;
        applicationUser = applicationUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("ApplicationUser not found with email : " + email)
                );


        return UserPrincipal.create(applicationUser);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        ApplicationUser applicationUser;
        applicationUser = applicationUserRepository.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException("ApplicationUser not found with id : " + id)
                );



        return UserPrincipal.create(applicationUser);
    }
}
