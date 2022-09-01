package core.di.bean;

import core.di.example.JdbcQuestionRepository;
import core.di.example.MyQnaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanConstructorTest {

    @DisplayName("클래스의 생성자에 @Inject 가 있다면, 생성자를 가져올 수 있다.")
    @Test
    void getInjectConstructor() {
        BeanDefinition definition = new DefaultBeanDefinition(
                MyQnaService.class
        );
        assertThat(
                definition.getInjectConstructor()
        ).isNotNull();
    }

    @DisplayName("클래스의 생성자에 @Inject 가 없다면, 생성자를 가져올 때 NoSuchElementException 이 발생한다.")
    @Test
    void nothing() {
        BeanDefinition definition = new DefaultBeanDefinition(
                JdbcQuestionRepository.class
        );
        assertThatThrownBy(
                () -> definition.getInjectConstructor()
        ).isInstanceOf(NoSuchElementException.class);
    }
}
