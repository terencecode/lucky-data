package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.List;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findById(Long id);
    Set<ApplicationUser> findAllByEmailIn(Collection<String> emails);
    boolean existsByEmail(String email);
    Optional<ApplicationUser> findByEmail(String email);
    void deleteByEmail(String email);
    List<ApplicationUser> findAll();
}
