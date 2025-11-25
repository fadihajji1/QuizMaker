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

    @GetMapping("")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/signin";
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userDetails.getUser();

        UpdateUserRequest dto = new UpdateUserRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        model.addAttribute("userDto", dto);
        model.addAttribute("userId", user.getUser_id());

        return "profile";
    }

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

    @PostMapping("/edit/{id}")
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


        UpdateUserRequest updatedDto = new UpdateUserRequest();
        updatedDto.setFirstName(user.getFirstName());
        updatedDto.setLastName(user.getLastName());
        updatedDto.setEmail(user.getEmail());

        model.addAttribute("userDto", updatedDto);
        model.addAttribute("userId", id);

        return "redirect:/dashboard/profile/edit/" + id;
    }
}
