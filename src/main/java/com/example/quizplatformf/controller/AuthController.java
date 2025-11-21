package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.loginRequest;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.quizplatformf.dto.request.SignupRequest;
import com.example.quizplatformf.dto.response.loginResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<?> signupJson(@RequestBody SignupRequest request) {
        User savedUser = createAndSaveUser(request);
        return ResponseEntity.ok("User created with id: " + savedUser.getUser_id());
    }


    @PostMapping(value = "/signup", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> signupForm(@ModelAttribute SignupRequest request) {
        User savedUser = createAndSaveUser(request);
        return ResponseEntity.ok("User created with id: " + savedUser.getUser_id());
    }

    private User createAndSaveUser(SignupRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return userService.createUser(user);
    }

    @PostMapping(value = "/signin", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> signinJson(@RequestBody loginRequest loginRequest) {

        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Error: Invalid password!");
        }

        loginResponse response = new loginResponse(user.getUser_id(), user.getEmail(), "Login successful");
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/signin", consumes = "application/x-www-form-urlencoded")
    public String signinForm(@ModelAttribute loginRequest loginRequest, Model model, RedirectAttributes redirectAttributes) {

        User user = userService.getUserByEmail(loginRequest.getEmail());

        if (user == null) {
            redirectAttributes.addFlashAttribute("loginError", "User not found!");
            return "redirect:/signin";
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            redirectAttributes.addFlashAttribute("loginError", "Invalid username or password!");
            return "redirect:/signin";
        }

        model.addAttribute("message", "Login successful!");
        model.addAttribute("user", user);
        return "redirect:/dashboard/";
    }

}
