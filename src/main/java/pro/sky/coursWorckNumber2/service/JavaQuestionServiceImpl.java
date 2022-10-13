package pro.sky.coursWorckNumber2.service;

import org.springframework.stereotype.Service;
import pro.sky.coursWorckNumber2.exception.QuestionAlreadyAddedException;
import pro.sky.coursWorckNumber2.exception.QuestionNotFoundException;
import pro.sky.coursWorckNumber2.model.Question;


import java.util.*;

@Service
public class JavaQuestionServiceImpl implements QuestionService {
    private final Set<Question> questionSet;
    private final Random random;

    public JavaQuestionServiceImpl() {
        this.questionSet = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add( String question, String answer ) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add( Question question ) {
        if (!questionSet.add(question)) {
            throw new QuestionAlreadyAddedException();
        }
        return question;
    }

    @Override
    public Question remove( Question question ) {
        if (!questionSet.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questionSet);
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questionSet).get(random.nextInt(questionSet.size()));
    }
}
