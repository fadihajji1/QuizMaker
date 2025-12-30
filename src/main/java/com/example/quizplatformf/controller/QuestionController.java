package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.QuestionForm;
import com.example.quizplatformf.entity.Question;
import com.example.quizplatformf.entity.Quiz;
import com.example.quizplatformf.service.QuestionService;
import com.example.quizplatformf.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/quiz")
public class QuestionController {

    private final QuizService quizService;
    private final QuestionService questionService;
    //Questions
    //Display Questions of a Quiz
    @GetMapping("/edit/{id}/questions")
    public String QuizQuestions(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        List<Question> listQuestions = questionService.getQuestionListByQuizId(id);
        model.addAttribute("questions", listQuestions);
        model.addAttribute("questionForm", new Question());
        return "manage-questions-answers";
    }

    //Add new Question to Quiz
    @PostMapping("/add/{id}/questions")
    public String addQuestions(@PathVariable Long id, @ModelAttribute QuestionForm questionForm) {
        Question question = new Question();
        question.setQuestion(questionForm.getQuestion());
        question.setPoints(questionForm.getPoints());
        question.setQuiz(quizService.getQuizById(id));
        questionService.createQuestion(question);
        return "redirect:/dashboard/quiz/edit/" + id + "/questions";
    }

    //Delete Question from Quiz
    @PostMapping("/edit/{quizId}/questions/delete/{questionId}")
    public String deleteQuestion(@PathVariable Long quizId, @PathVariable Long questionId) {
        questionService.deleteQuestionById(questionId);
        return "redirect:/dashboard/quiz/edit/" + quizId + "/questions";
    }
}
