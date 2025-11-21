package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.loginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/add-quiz")
    public String addQuiz() {
        return "add-quiz";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
