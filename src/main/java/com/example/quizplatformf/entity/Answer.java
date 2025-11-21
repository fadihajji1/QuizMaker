package com.example.quizplatformf.entity;

import jakarta.persistence.*;

@Entity
@Table(name="answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private String answer;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question questionId;

}
