package com.pixisphere.repository;

import com.pixisphere.entity.AppUser;
import com.pixisphere.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    long countByRole(Role role);
}
