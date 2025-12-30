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
    private Quiz quiz;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public Question() {}

    public Question(String question, Type type, int points, Quiz quiz) {
        this.question = question;
        this.type = type;
        this.points = points;
        this.quiz = quiz;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public Type getType() {
        return type;
    }

    public int getPoints() {
        return points;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
