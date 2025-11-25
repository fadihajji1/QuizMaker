package com.example.quizplatformf.controller;

import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.security.CustomUserDetails;
import com.example.quizplatformf.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public String createQuiz(
            @ModelAttribute Quiz quiz,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Quiz savedQuiz = quizService.createQuiz(quiz, userId);

        return "redirect:/quiz/" + savedQuiz.getQuizId() + "/edit";
    }

    @GetMapping("/{id}/edit")
    public String editQuizPage(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        return "quiz-edit";
    }

    @PostMapping("/{id}/update")
    public String updateQuiz(@PathVariable Long id, @ModelAttribute Quiz updatedQuiz) {
        updatedQuiz.setQuizId(id);
        quizService.updateQuiz(updatedQuiz);
        return "redirect:/quiz/" + id + "/edit?updated=true";
    }

    @PostMapping("/{id}/delete")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return "redirect:/dashboard?deleted=true";
    }
}
