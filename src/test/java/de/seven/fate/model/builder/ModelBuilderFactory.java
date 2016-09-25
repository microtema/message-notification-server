package de.seven.fate.model.builder;

import java.util.Optional;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notNull;

public final class ModelBuilderFactory {

    public static <T> ModelBuilder<T> create(Class<T> modelType) {
        return new AbstractModelBuilder<T>() {
            @Override
            public Class<T> getTargetType() {

                return modelType;
            }
        };
    }

    public static <T> ModelBuilder<T> findOrCreate(Class<T> modelType, Set<ModelBuilder> builders) {
        notNull(modelType);
        notNull(builders);

        Optional<ModelBuilder> optional = builders.stream().filter(builder -> builder.getTargetType() == modelType).findFirst();

        if (optional.isPresent()) {
            return optional.get();
        }

        ModelBuilder<T> modelBuilder = create(modelType);

        builders.add(modelBuilder);

        return modelBuilder;
    }
}
