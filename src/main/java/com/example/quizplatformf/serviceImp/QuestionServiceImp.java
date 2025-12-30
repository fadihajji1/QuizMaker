package com.example.quizplatformf.serviceImp;

import com.example.quizplatformf.entity.Question;
import com.example.quizplatformf.repository.QuestionRepository;
import com.example.quizplatformf.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionListByQuizId(Long id) {return questionRepository.findByQuiz_QuizId(id);}

}
