package de.seven.fate.converter;

import de.seven.fate.util.ClassUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public abstract class AbstractModelConverter<D, O> implements ModelConverter<D, O> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractModelConverter.class.getCanonicalName());

    @Override
    public D convert(O orig) {
        if (orig == null) {
            return null;
        }

        Class<D> genericType = getDestinationType();

        D instance = ClassUtil.newInstance(genericType);

        update(instance, orig);

        return instance;
    }

    @Override
    public void update(D dest, O orig) {
        notNull(dest);

        if (orig == null) {
            return;
        }

        try {
            PropertyUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.warn("unable to copy properties from: " + orig + " to " + dest, e);
        }

    }

    @Override
    public List<D> convertList(Collection<O> entries) {
        if (isEmpty(entries)) {
            return Collections.emptyList();
        }

        return entries.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Set<D> convertSet(Collection<O> entries) {
        if (isEmpty(entries)) {
            return Collections.emptySet();
        }

        return entries.stream().map(this::convert).collect(Collectors.toSet());
    }

    @Override
    public Class<D> getDestinationType() {

        return ClassUtil.getGenericType(getClass(), 0);
    }

}