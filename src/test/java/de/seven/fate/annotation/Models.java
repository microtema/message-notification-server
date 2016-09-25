package de.seven.fate.annotation;

import de.seven.fate.enums.ModelsType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Models {

    ModelsType type() default ModelsType.LIST;

    int size() default -1;
}
