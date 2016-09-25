package de.seven.fate.processor;

import de.seven.fate.annotation.Models;
import de.seven.fate.model.ModelBuilder;
import de.seven.fate.util.ClassUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;

import static org.apache.camel.spring.util.ReflectionUtils.setField;
import static org.springframework.util.ReflectionUtils.doWithFields;
import static org.springframework.util.ReflectionUtils.makeAccessible;

@Component
public class ModelsProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        doWithFields(bean.getClass(), field -> {

            makeAccessible(field);

            Object value = getModelsValue(ClassUtil.getGenericType(field.getGenericType(), 0), field.getAnnotation(Models.class));
            setField(field, bean, value);

        }, field -> field.isAnnotationPresent(Models.class));


        return bean;
    }

    private <T> Collection<T> getModelsValue(Class<T> modelType, Models models) {

        ModelBuilder<T> modelBuilder = modelBuilderSet.stream().filter((builder) -> builder.getTargetType() == modelType).findFirst().get();

        switch (models.type()) {
            case LIST:
                return modelBuilder.list(models.size());
            case SET:
                return modelBuilder.set(models.size());
        }

        throw new IllegalStateException("Unsupported models type: " + models.type());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Inject
    private Set<ModelBuilder> modelBuilderSet;
}
