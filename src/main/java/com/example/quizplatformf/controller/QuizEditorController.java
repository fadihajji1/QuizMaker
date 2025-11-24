package com.example.quizplatformf.controller;

import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.repository.QuizRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizEditorController {
    private final QuizRepository quizRepository;

    public QuizEditorController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/dashboard/quiz/{id}")
    public String quizEditor(@PathVariable Long id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        model.addAttribute("quiz", quiz);
        return "quiz-edit";
    }
}
