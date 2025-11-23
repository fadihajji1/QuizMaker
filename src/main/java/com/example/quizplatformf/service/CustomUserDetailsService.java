package com.example.quizplatformf.service;

import com.example.quizplatformf.entity.User;
import com.example.quizplatformf.repository.UserRepository;
import com.example.quizplatformf.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail((email));

        if (user == null)
            throw new UsernameNotFoundException("User not found!");

        return new CustomUserDetails(user);
    }
}
