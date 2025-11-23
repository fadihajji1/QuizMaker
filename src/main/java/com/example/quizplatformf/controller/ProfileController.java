package com.example.quizplatformf.controller;

import com.example.quizplatformf.dto.request.UpdateUserRequest;
import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.repository.UserRepository;
import com.example.quizplatformf.security.CustomUserDetails;
import com.example.quizplatformf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Display the profile page
    @GetMapping("")
    public String profile(Model model) {
        // Get the currently authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/signin";
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userDetails.getUser();

        // Populate DTO for the form
        UpdateUserRequest dto = new UpdateUserRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        model.addAttribute("userDto", dto);
        model.addAttribute("userId", user.getUser_id());

        return "profile";
    }

    // Show edit form for a specific user (optional, if needed separately)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UpdateUserRequest dto = new UpdateUserRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        model.addAttribute("userDto", dto);
        model.addAttribute("userId", id);

        return "profile";
    }

    // Handle update submission
    @PostMapping("/edit/{id}") // Use POST for form submission (PUT may need JS/Ajax)
    public String updateUser(
            @PathVariable Long id,
            @ModelAttribute("userDto") UpdateUserRequest dto,
            Model model
    ) {
        User user = userService.getUserById(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        userRepository.save(user);

        // Add updated user info back to the model
        UpdateUserRequest updatedDto = new UpdateUserRequest();
        updatedDto.setFirstName(user.getFirstName());
        updatedDto.setLastName(user.getLastName());
        updatedDto.setEmail(user.getEmail());

        model.addAttribute("userDto", updatedDto);
        model.addAttribute("userId", id);

        // Redirect to refresh the page with updated data
        return "redirect:/dashboard/profile/edit/" + id;
    }
}
