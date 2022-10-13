package pro.sky.coursWorckNumber2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursWorckNumber2.model.Question;
import pro.sky.coursWorckNumber2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController( QuestionService questionService ) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion( @RequestParam String question,
                                 @RequestParam String answer ) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion( @RequestParam String question,
                                    @RequestParam String answer ) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping
    Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
