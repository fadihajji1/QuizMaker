package com.example.quizplatformf.repository;

import com.example.quizplatformf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findByEmail(String email);
}
