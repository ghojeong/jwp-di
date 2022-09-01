package core.di.bean;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanFields {

    private static final BeanFields emptyBeanFields =
            new BeanFields(Collections.emptySet());

    private final Set<Field> fields;

    private BeanFields(Set<Field> fields) {
        this.fields = fields;
    }

    public static BeanFields empty() {
        return emptyBeanFields;
    }

    public static BeanFields of(Class<?> clazz) {
        InjectTypes injectTypes = InjectTypes.of(clazz);
        return new BeanFields(
                Arrays.stream(clazz.getDeclaredFields())
                        .filter(injectTypes::isInjectField)
                        .collect(Collectors.toSet())
        );
    }

    public boolean isFieldMode() {
        return !fields.isEmpty();
    }

    public Set<Field> toSet() {
        return Set.copyOf(fields);
    }
}
