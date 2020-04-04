package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Set<User> findAllByEmailIn(Collection<String> emails);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
}
