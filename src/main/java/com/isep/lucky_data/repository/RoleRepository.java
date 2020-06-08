package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.Role;
import com.isep.lucky_data.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleName roleName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_roles u WHERE u.user_id = ?1 AND u.role_id = ?2", nativeQuery = true)
    void deleteUserRole(Long userId, Integer RoleId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_roles VALUES (?1, ?2)", nativeQuery = true)
    void addUserRole(Long userId, Integer RoleId);
}
