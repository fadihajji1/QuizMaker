package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.QuestionForm;
import com.example.quizplatformf.dto.QuizForm;
import com.example.quizplatformf.entity.Question;
import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.security.CustomUserDetails;
import com.example.quizplatformf.service.QuestionService;
import com.example.quizplatformf.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/quiz")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    @GetMapping("/list")
    public String listQuiz(
            Model model,
            Authentication authentication
    ){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<Quiz> listQuizzes = quizService.getQuizzesByUserId(userId);
        model.addAttribute("quizzes", listQuizzes);
        model.addAttribute("userId", userId);
        return "quiz-list";
    }

    @GetMapping("/add")
    public String addQuiz(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {

        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getId());
            model.addAttribute("quizForm", new QuizForm());
        }
        return "add-quiz";
    }

    @PostMapping("/create")
    public String createQuiz(
            @ModelAttribute QuizForm quizForm,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Duration duration = quizForm.toDuration();
        if (duration.isNegative() || duration.isZero()) {
            duration = Duration.ofMinutes(3);
        }
        Quiz quiz = new Quiz();

        quiz.setTitle(quizForm.getTitle());
        quiz.setDescription(quizForm.getDescription());
        quiz.setDuration(duration);

        quizService.createQuiz(quiz, userId);

        return "redirect:/dashboard/quiz/list";
    }

    @GetMapping("/edit/{id}")
    public String editQuizPage(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        return "quiz-edit";
    }


    @PostMapping("/update/{id}")
    public String updateQuiz(@PathVariable Long id, @ModelAttribute Quiz updatedQuiz) {
        updatedQuiz.setQuizId(id);
        quizService.updateQuiz(updatedQuiz);
        return "redirect:/dashboard/quiz/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return "redirect:/dashboard/quiz/list";
    }
}
