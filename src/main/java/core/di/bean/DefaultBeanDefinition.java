package core.di.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

public class DefaultBeanDefinition implements BeanDefinition {
    private final Class<?> beanClass;
    private final BeanConstructor injectConstructor;
    private final BeanFields injectFields;
    private final InjectMode resolvedInjectMode;

    public DefaultBeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.injectConstructor = BeanConstructor.of(beanClass);
        this.injectFields = injectConstructor.isConstructorMode()
                ? BeanFields.empty()
                : BeanFields.of(beanClass);
        this.resolvedInjectMode = InjectMode.from(injectConstructor, injectFields);
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public Constructor<?> getInjectConstructor() {
        return injectConstructor.toConstructor();
    }

    @Override
    public Set<Field> getInjectFields() {
        return injectFields.toSet();
    }

    @Override
    public InjectMode getResolvedInjectMode() {
        return resolvedInjectMode;
    }
}
