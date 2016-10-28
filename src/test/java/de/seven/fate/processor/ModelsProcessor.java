package de.seven.fate.processor;

import de.seven.fate.annotation.Models;
import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import de.seven.fate.util.ClassUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;

import static org.springframework.util.ReflectionUtils.*;

@Component
public class ModelsProcessor implements BeanPostProcessor {

    @Inject
    private Set<ModelBuilder> modelBuilders;

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

        ModelBuilder<T> modelBuilder = ModelBuilderFactory.findOrCreate(modelType, modelBuilders);

        switch (models.type()) {
            case LIST:
                return modelBuilder.list(models.size());
            case FIX_LIST:
                return modelBuilder.fixList();
            case SET:
                return modelBuilder.set(models.size());
            case FIX_SET:
                return modelBuilder.fixSet();
        }

        throw new IllegalStateException("Unsupported models type: " + models.type());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
