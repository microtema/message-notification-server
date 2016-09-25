package de.seven.fate.annotation;
import de.seven.fate.enums.ModelType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
    ModelType type() default ModelType.MIN;
}
