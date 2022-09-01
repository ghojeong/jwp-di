package core.di.bean;

public enum InjectMode {
    INJECT_CONSTRUCTOR,
    INJECT_FIELD,
    INJECT_NOTHING;

    public static InjectMode from(
            BeanConstructor injectConstructor,
            BeanFields injectFields
    ) {
        return injectConstructor.isConstructorMode()
                ? InjectMode.INJECT_CONSTRUCTOR
                : injectFields.isFieldMode()
                ? InjectMode.INJECT_FIELD
                : InjectMode.INJECT_NOTHING;
    }
}
