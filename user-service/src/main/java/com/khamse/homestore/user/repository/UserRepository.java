package com.khamse.homestore.user.repository; // Updated package


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khamse.homestore.common.model.User; // Updated import



// @Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
