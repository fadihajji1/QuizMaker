package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.QuizForm;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/signin";
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("userId", userDetails.getUser().getUser_id());
        return "dashboard";
    }

}
