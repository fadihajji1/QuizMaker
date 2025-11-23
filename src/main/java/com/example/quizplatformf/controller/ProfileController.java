package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.SignupRequest;
import com.example.quizplatformf.dto.request.UpdateUserRequest;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.repository.UserRepository;
import com.example.quizplatformf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/profile")
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String profile() {
        return "profile";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        UpdateUserRequest dto = new UpdateUserRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        model.addAttribute("userDto", dto);
        model.addAttribute("userId", id);

        return "profile";
    }

    // Handle update submission
    @PostMapping("/update/{id}")
    public String updateUser(
            @PathVariable Long id,
            @ModelAttribute("userDto") UpdateUserRequest dto
    ) {
        User user = userService.getUserById(id);
       //User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        userRepository.save(user);

        return "redirect:/user/profile/" + id;
    }

}
