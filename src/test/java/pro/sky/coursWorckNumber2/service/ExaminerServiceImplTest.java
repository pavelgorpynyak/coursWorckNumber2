package pro.sky.coursWorckNumber2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursWorckNumber2.exception.IncorrectAmountOfQuestionQuantity;
import pro.sky.coursWorckNumber2.model.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final static int SIZE = 5;

    private List<Question> questions;

    @Mock
    private JavaQuestionServiceImpl questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        questions = Stream.iterate(1, i -> +1)
                .limit(SIZE)
                .map(i -> new Question("q" + i, "a" + i))
                .collect(Collectors.toList());

        lenient().when(questionService.getAll())
                .thenReturn(questions);
    }

    @ParameterizedTest
    @MethodSource("incorrectAmounts")
    public void getQuestionNegative( int incorrectAmount ) {
        assertThatExceptionOfType(IncorrectAmountOfQuestionQuantity.class)
                .isThrownBy(() -> examinerService.getQuestions(incorrectAmount));
    }

    @Test
    public void getQuestionPositive() {
        when(questionService.getRandomQuestion())
                .thenReturn(
                        questions.get(0),
                        questions.get(0),
                        questions.get(1),
                        questions.get(3),
                        questions.get(4),
                        questions.get(2)
                );
        Assertions.assertThat(examinerService.getQuestions(5))
                .containsExactly(
                        questions.get(0),
                        questions.get(1),
                        questions.get(3),
                        questions.get(4),
                        questions.get(2)

                );

        when(questionService.getRandomQuestion())
                .thenReturn(
                        questions.get(4),
                        questions.get(0),
                        questions.get(4),
                        questions.get(0),
                        questions.get(4),
                        questions.get(2)
                );
        Assertions.assertThat(examinerService.getQuestions(3))
                .containsExactly(
                        questions.get(4),
                        questions.get(0),
                        questions.get(2)
                );
    }

    public static Stream<Arguments> incorrectAmounts() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(SIZE + 100),
                Arguments.of(SIZE + 1)
        );
    }
}