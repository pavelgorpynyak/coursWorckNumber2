package pro.sky.coursWorckNumber2.service;

import org.springframework.stereotype.Service;
import pro.sky.coursWorckNumber2.exception.IncorrectAmountOfQuestionQuantity;
import pro.sky.coursWorckNumber2.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    QuestionService questionService;

    public ExaminerServiceImpl( QuestionService questionService ) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions( int amount ) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountOfQuestionQuantity();
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
