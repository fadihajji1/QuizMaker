package com.example.quizplatformf.service;

import com.example.quizplatformf.entity.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    List<Quiz> getQuizList();
    Quiz createQuiz(Quiz quiz, Long userId);
    Quiz updateQuiz(Quiz quiz);
    Quiz getQuizById(Long id);
    void deleteQuizById(Long id);
    List<Quiz> getQuizzesByUserId(Long userId); // get list of quizzes per user
}
