package de.seven.fate.processor;

import de.seven.fate.annotation.Model;
import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Set;

import static org.springframework.util.ReflectionUtils.*;

@Component
public class ModelProcessor implements BeanPostProcessor {

    @Inject
    private Set<ModelBuilder> modelBuilders;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        doWithFields(bean.getClass(), field -> {

            makeAccessible(field);

            Object value = getModelValue(field.getType(), field.getAnnotation(Model.class));
            setField(field, bean, value);

        }, field -> field.isAnnotationPresent(Model.class));


        return bean;
    }

    private <T> T getModelValue(Class<T> modelType, Model model) {

        ModelBuilder<T> modelBuilder = ModelBuilderFactory.findOrCreate(modelType, modelBuilders);

        switch (model.type()) {
            case MIN:
                return modelBuilder.min();
            case MAX:
                return modelBuilder.max();
            case RANDOM:
                return modelBuilder.random();
            case FIX:
                return modelBuilder.fix();
        }

        throw new IllegalStateException("Unsupported model type: " + model.type());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
