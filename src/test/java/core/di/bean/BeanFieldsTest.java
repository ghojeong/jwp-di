package core.di.bean;

import core.di.example.BadMethodController;
import core.di.example.FieldController;
import core.di.example.MethodController;
import core.di.example.MyQnaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanFieldsTest {
    @DisplayName("클래스의 필드에 @Inject 가 있다면, 필드를 가져올 수 있다.")
    @Test
    void field() {
        BeanDefinition definition = new DefaultBeanDefinition(
                FieldController.class
        );
        assertThat(
                definition.getInjectFields()
        ).isNotEmpty();
    }

    @DisplayName("클래스의 setter 메서드에 @Inject 가 있다면, 필드를 가져올 수 있다.")
    @Test
    void setterField() {
        BeanDefinition definition = new DefaultBeanDefinition(
                MethodController.class
        );
        assertThat(
                definition.getInjectFields()
        ).isNotEmpty();
    }

    @DisplayName("클래스의 @Inject 가 붙은 setter 메서드에 파라미터가 2개 이상이면, IllegalStateException 이 발생한다..")
    @Test
    void badSetterField() {
        assertThatThrownBy(
                () -> new DefaultBeanDefinition(BadMethodController.class)
        ).hasMessage("DI할 메소드 인자는 하나여야 합니다.")
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("클래스의 필드 혹은 setter 에 @Inject 가 없다면, 필드를 가져올 수 없다.")
    @Test
    void noField() {
        BeanDefinition definition = new DefaultBeanDefinition(
                MyQnaService.class
        );
        assertThat(
                definition.getInjectFields()
        ).isEmpty();
    }
}
