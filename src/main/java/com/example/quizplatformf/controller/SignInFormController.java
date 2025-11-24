package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.SignupRequest;
import com.example.quizplatformf.dto.request.loginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInFormController {
    @GetMapping("/signin")
    public String signinForm(Model model, @ModelAttribute("loginError") String loginError,
                             @RequestParam(value = "error", required = false) Boolean error,
                             @RequestParam(value = "logout", required = false) Boolean logout) {
        if (!model.containsAttribute("loginRequest")) {
            model.addAttribute("loginRequest", new loginRequest());
        }
        if (!loginError.isEmpty()) {
            model.addAttribute("loginError", loginError);
        }
        if (error != null && error) {
            model.addAttribute("loginError", "Invalid credentials");
        }
        if (logout != null && logout) {
            model.addAttribute("message", "Logged out successfully");
        }
        return "signin";
    }

}
