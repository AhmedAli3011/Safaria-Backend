package com.safaria.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safaria.backend.CustomExceptions.AlreadyExistsException;
import com.safaria.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "tourProvider")
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}