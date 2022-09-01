package core.di.bean;

import core.di.example.FieldController;
import core.di.example.JdbcQuestionRepository;
import core.di.example.MethodController;
import core.di.example.MyQnaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InjectModeTest {

    @DisplayName("클래스에 @Inject 가 없다면, INJECT_NOTHING 이다")
    @Test
    void nothing() {
        BeanDefinition definition = new DefaultBeanDefinition(
                JdbcQuestionRepository.class
        );
        assertThat(
                definition.getResolvedInjectMode()
        ).isEqualTo(InjectMode.INJECT_NOTHING);
    }

    @DisplayName("클래스의 생성자에 @Inject 가 있다면, INJECT_CONSTRUCTOR 이다")
    @Test
    void constructor() {
        BeanDefinition definition = new DefaultBeanDefinition(
                MyQnaService.class
        );
        assertThat(
                definition.getResolvedInjectMode()
        ).isEqualTo(InjectMode.INJECT_CONSTRUCTOR);
    }

    @DisplayName("클래스의 필드에 @Inject 가 있다면, INJECT_FIELD 이다")
    @Test
    void field() {
        BeanDefinition definition = new DefaultBeanDefinition(
                FieldController.class
        );
        assertThat(
                definition.getResolvedInjectMode()
        ).isEqualTo(InjectMode.INJECT_FIELD);
    }

    @DisplayName("클래스의 setter 메서드에 @Inject 가 있다면, INJECT_FIELD 이다")
    @Test
    void setterField() {
        BeanDefinition definition = new DefaultBeanDefinition(
                MethodController.class
        );
        assertThat(
                definition.getResolvedInjectMode()
        ).isEqualTo(InjectMode.INJECT_FIELD);
    }
}
