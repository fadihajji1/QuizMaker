package com.example.quizplatformf.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String question;
    private Enum type;
    private int points;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quizId;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();
}
