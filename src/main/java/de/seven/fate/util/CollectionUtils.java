package de.seven.fate.util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mario on 03.10.2016.
 */
@UtilityClass
public class CollectionUtils {

    public <K, V> Map<K, V> toMap(K key, V value) {

        Map<K, V> map = new HashMap();

        map.put(key, value);

        return map;
    }
}
