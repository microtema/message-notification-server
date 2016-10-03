package de.seven.fate.model.builder;

import java.util.List;
import java.util.Set;

public interface ModelBuilder<T> {

    /**
     * @return Generic Model Typ
     */
    Class<T> getTargetType();

    /**
     * @return new Instance of Model and init only required fields
     */
    T min();

    /**
     * @return new Instance of Model and init all fields
     */
    T max();

    /**
     * @return new Instance of Model and init as Min or Max
     */
    T random();

    /**
     * @return new Instance of Model and init within fix values
     */
    T fix();

    /**
     * @return new List by random size with new Instances of Models and init as Min or Max
     */
    List<T> list();

    /**
     * @param size of Collection
     * @return new Set by random size with new Instances of Models and init as Min or Max
     */
    List<T> list(int size);

    /**
     * @return new Set by fix size with new Instances of Models and init as Fix
     */
    List<T> fixList();

    /**
     * @return new Set by random size with new Instances of Models and init as Min or Max
     */
    Set<T> set();

    /**
     * @param size of Collection
     * @return new Set by fixed size with new Instances of Models and init as Min or Max
     */
    Set<T> set(int size);

    /**
     * @return new List by fix size with new Instances of Models and init as Fix
     */
    Set<T> fixSet();
}
