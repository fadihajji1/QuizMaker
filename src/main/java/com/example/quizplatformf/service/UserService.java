package com.example.quizplatformf.service;

import com.example.quizplatformf.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getUserList();
    void deleteUser(User user);
    User updateUser(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
}
