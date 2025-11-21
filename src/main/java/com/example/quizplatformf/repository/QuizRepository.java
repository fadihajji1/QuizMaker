package com.example.quizplatformf.repository;

import com.example.quizplatformf.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByUser_userId(Long userId);
}
