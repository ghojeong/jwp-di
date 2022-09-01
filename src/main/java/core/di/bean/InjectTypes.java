package core.di.bean;

import core.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.util.ReflectionUtilsPredicates.withAnnotation;
import static org.reflections.util.ReflectionUtilsPredicates.withReturnType;

public class InjectTypes {
    private final Set<Class<?>> injectTypes;

    private InjectTypes(Set<Class<?>> injectTypes) {
        this.injectTypes = injectTypes;
    }

    public boolean isInjectField(Field field) {
        return injectTypes.contains(field.getType());
    }

    public static InjectTypes of(Class<?> clazz) {
        Set<Class<?>> injectTypes = getInjectTypesFromMethods(clazz);
        injectTypes.addAll(getInjectTypesFromFields(clazz));
        return new InjectTypes(injectTypes);
    }

    private static Set<Class<?>> getInjectTypesFromFields(Class<?> clazz) {
        return getAllFields(
                clazz,
                withAnnotation(Inject.class)
        ).stream()
                .map(Field::getType)
                .collect(Collectors.toSet());
    }

    private static Set<Class<?>> getInjectTypesFromMethods(Class<?> clazz) {
        return getAllMethods(
                clazz,
                withAnnotation(Inject.class),
                withReturnType(void.class)
        ).stream()
                .map(InjectTypes::getInjectTypeFromMethod)
                .collect(Collectors.toSet());
    }

    private static Class<?> getInjectTypeFromMethod(Method method) {
        Class<?>[] types = method.getParameterTypes();
        if (types.length != 1) {
            throw new IllegalStateException("DI할 메소드 인자는 하나여야 합니다.");
        }
        return types[0];
    }
}
