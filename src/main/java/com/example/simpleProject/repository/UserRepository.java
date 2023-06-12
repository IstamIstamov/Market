package com.example.simpleProject.repository;

import com.example.simpleProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserIdAndDeleteAtIsNull(Integer userId);
}
