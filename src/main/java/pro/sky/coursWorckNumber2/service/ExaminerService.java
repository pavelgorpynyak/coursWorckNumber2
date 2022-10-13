package pro.sky.coursWorckNumber2.service;

import pro.sky.coursWorckNumber2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions( int amount );
}
