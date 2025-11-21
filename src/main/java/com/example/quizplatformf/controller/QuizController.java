package com.example.quizplatformf.controller;

import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Quiz> createQuiz(
            @PathVariable Long userId,
            @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz, userId));
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return ResponseEntity.ok(quizService.getQuizList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
                return ResponseEntity.ok(quizService.updateQuiz(updatedQuiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Quiz>> getQuizByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(quizService.getQuizByUserId(userId));
    }
}
