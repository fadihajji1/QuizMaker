package com.example.quizplatformf.serviceImp;


import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.repository.QuizRepository;
import com.example.quizplatformf.repository.UserRepository;
import com.example.quizplatformf.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceImp implements QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Quiz> getQuizList() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz createQuiz(Quiz quiz, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        quiz.setUser(user);

        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    public Quiz updateQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long id){
        return quizRepository.findById(id).get();
    }

    public void deleteQuizById(Long id){
        quizRepository.deleteById(id);
    }

    public List<Quiz> getQuizzesByUserId(Long userId){
        return quizRepository.findByUser_userId(userId);
    }
}
