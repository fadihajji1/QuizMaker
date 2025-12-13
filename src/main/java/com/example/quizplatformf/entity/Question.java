package com.example.quizplatformf.entity;

import com.example.quizplatformf.entity.enums.Type;
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
    @Enumerated(EnumType.STRING)
    private Type type;
    private int points;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quizId;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();
}
