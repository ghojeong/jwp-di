package core.di.bean;

import core.annotation.Inject;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllConstructors;
import static org.reflections.util.ReflectionUtilsPredicates.withAnnotation;

public class BeanConstructor {
    private final Optional<Constructor<?>> optionalConstructor;

    private BeanConstructor(Constructor<?> optionalConstructor) {
        this.optionalConstructor = Optional.ofNullable(optionalConstructor);
    }

    public static BeanConstructor of(Class<?> clazz) {
        Set<Constructor> injectConstructors = getAllConstructors(clazz, withAnnotation(Inject.class));
        return new BeanConstructor(
                injectConstructors.isEmpty()
                        ? null
                        : injectConstructors.iterator().next()
        );
    }

    public boolean isConstructorMode() {
        return optionalConstructor.isPresent();
    }

    public Constructor<?> toConstructor() {
        return optionalConstructor.get();
    }
}
