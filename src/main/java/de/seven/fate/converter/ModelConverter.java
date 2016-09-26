package de.seven.fate.converter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ModelConverter<D, O> {

    D convert(O orig);

    void update(D dest, O orig);

    List<D> convertList(Collection<O> entries);

    Set<D> convertSet(Collection<O> entries);
}
