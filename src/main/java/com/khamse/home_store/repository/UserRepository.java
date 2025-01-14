package com.khamse.home_store.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khamse.home_store.model.User;

// @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
