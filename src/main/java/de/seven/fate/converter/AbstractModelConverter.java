package de.seven.fate.converter;

import de.seven.fate.util.ClassUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        } catch (Exception any) {
            LOGGER.warn("unable to copy properties from: " + orig + " to " + dest);
        }

    }

    public List<D> convertList(Collection<O> entries) {
        if (isEmpty(entries)) {
            return Collections.emptyList();
        }

        return entries.stream().map(this::convert).collect(Collectors.toList());
    }

    public Set<D> convertSet(Collection<O> entries) {
        if (isEmpty(entries)) {
            return Collections.emptySet();
        }

        return entries.stream().map(this::convert).collect(Collectors.toSet());
    }

    public Class<D> getDestinationType() {

        return ClassUtil.getGenericType(getClass(), 0);
    }

}