package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.SignupRequest;
import com.example.quizplatformf.dto.request.loginRequest;
import com.example.quizplatformf.dto.response.loginResponse;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.service.UserService;
import com.example.quizplatformf.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ------------------ SIGNUP ------------------

    // Signup JSON API
    @PostMapping(value = "/signup", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> signupJson(@RequestBody SignupRequest request) {
        User savedUser = createAndSaveUser(request);
        return ResponseEntity.ok("User created with id: " + savedUser.getUser_id());
    }

    // Signup Form
    @PostMapping(value = "/signup", consumes = "application/x-www-form-urlencoded")
    public String signupForm(@ModelAttribute SignupRequest request, RedirectAttributes redirectAttributes) {
        createAndSaveUser(request);
        redirectAttributes.addFlashAttribute("message", "Signup successful! Please login.");
        return "redirect:/signin";
    }

    private User createAndSaveUser(SignupRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );
        return userService.createUser(user);
    }

    // ------------------ SIGNIN ------------------

    // JSON signin
    @PostMapping(value = "/signin", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> signinJson(@RequestBody loginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user == null) return ResponseEntity.badRequest().body("Error: User not found!");
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            return ResponseEntity.badRequest().body("Error: Invalid password!");
        loginResponse response = new loginResponse(user.getUser_id(), user.getEmail(), "Login successful");
        return ResponseEntity.ok(response);
    }

    // Form signin
    @PostMapping(value = "/signin", consumes = "application/x-www-form-urlencoded")
    public String signinForm() {
        return "redirect:/dashboard/";
    }
}
