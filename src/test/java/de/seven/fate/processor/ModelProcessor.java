package de.seven.fate.processor;

import de.seven.fate.annotation.Model;
import de.seven.fate.model.ModelBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Set;

import static org.apache.camel.spring.util.ReflectionUtils.setField;
import static org.springframework.util.ReflectionUtils.doWithFields;
import static org.springframework.util.ReflectionUtils.makeAccessible;

@Component
public class ModelProcessor implements BeanPostProcessor {

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

        ModelBuilder<T> modelBuilder = modelBuilderSet.stream().filter((builder) -> builder.getTargetType() == modelType).findFirst().get();

        switch (model.type()) {
            case MIN:
                return modelBuilder.min();
            case MAX:
                return modelBuilder.max();
            case RANDOM:
                return modelBuilder.random();
        }

        throw new IllegalStateException("Unsupported model type: " + model.type());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Inject
    private Set<ModelBuilder> modelBuilderSet;
}
