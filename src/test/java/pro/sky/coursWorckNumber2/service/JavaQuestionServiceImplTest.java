package pro.sky.coursWorckNumber2.service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursWorckNumber2.exception.QuestionAlreadyAddedException;
import pro.sky.coursWorckNumber2.exception.QuestionNotFoundException;
import pro.sky.coursWorckNumber2.model.Question;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class JavaQuestionServiceImplTest {
    private final QuestionService questionService = new JavaQuestionServiceImpl();

    @AfterEach
    public void afterEach() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        assertThat(questionService.getAll().isEmpty());
        Question question1 = add("question", "answer");

        assertThat(questionService.getAll())
                .hasSize(1)
                .contains(question1);

        Question question2 = new Question("question2", "answer2");
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(question1.getQuestion(), question1.getAnswer()));
        questionService.add(question2.getQuestion(), question2.getAnswer());

        assertThat(questionService.getAll())
                .hasSize(2)
                .contains(question1, question2);
    }

    @Test
    public void removeTest() {
        assertThat(questionService.getAll().isEmpty());
        Question question = add("question", "answer");

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("q1", "a1")));

        assertThat(questionService.getAll())
                .hasSize(1)
                .contains(question);

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("q2", "a2")));

        questionService.remove(question);
        assertThat(questionService.getAll().isEmpty());
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getAll().isEmpty());
        Question question1 = add("question1", "answer1");
        Question question2 = add("question2", "answer2");
        Question question3 = add("question3", "answer3");
        Question question4 = add("question4", "answer4");
        Question question5 = add("question5", "answer5");

        assertThat(questionService.getAll())
                .hasSize(5)
                .contains(questionService.getRandomQuestion());

    }

    public Question add( String question, String answer ) {
        return questionService.add(question, answer);
    }
}