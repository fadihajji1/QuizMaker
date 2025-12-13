package com.example.quizplatformf.dto;

import com.example.quizplatformf.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String question;
    private Type type;
    private int points;
}
