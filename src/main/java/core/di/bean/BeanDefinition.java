package core.di.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

public interface BeanDefinition {
    Class<?> getBeanClass();

    Constructor<?> getInjectConstructor();

    Set<Field> getInjectFields();

    InjectMode getResolvedInjectMode();
}
