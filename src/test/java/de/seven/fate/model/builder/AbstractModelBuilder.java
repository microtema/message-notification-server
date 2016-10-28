package de.seven.fate.model.builder;

import java.util.*;

import static de.seven.fate.util.ClassUtil.getGenericType;
import static de.seven.fate.util.ClassUtil.newInstance;

public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    private static final int MIN_COLLECTION_SIZE = 1;
    private static final int MAX_COLLECTION_SIZE = 10;
    private static final int AUTO_COLLECTION_SIZE = -1;


    @Override
    public T min() {

        T instance = newInstance(getTargetType());

        return instance;
    }

    @Override
    public T max() {
        return min();
    }

    @Override
    public T random() {
        return random(new Random().nextBoolean());
    }

    @Override
    public T fix() {
        return min();
    }

    @Override
    public List<T> list() {

        return list(randomCollectionSize());
    }

    @Override
    public List<T> list(int size) {

        List<T> list = new ArrayList<>();

        fillCollection(size, list, this::random);

        return list;
    }

    @Override
    public List<T> fixList() {

        List<T> list = new ArrayList<>();

        fillCollection(MAX_COLLECTION_SIZE/2, list, this::fix);

        return list;
    }



    @Override
    public Set<T> set(int size) {

        Set<T> set = new HashSet<>();

        fillCollection(size, set, this::random);

        return set;
    }

    @Override
    public Set<T> set() {

        return set(randomCollectionSize());
    }

    @Override
    public Set<T> fixSet() {

        Set<T> list = new HashSet<>();

        fillCollection(MAX_COLLECTION_SIZE/2, list, this::fix);

        return list;
    }

    /*
     * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
     */
    private void fillCollection(int size, Collection<T> collection, GenerateModel<T> generateModel) {

        if (AUTO_COLLECTION_SIZE == size) {
            size = randomCollectionSize();
        }

        int count = 0;
        while (count++ < size) {
            collection.add(generateModel.generate());
        }
    }



    @Override
    public Class<T> getTargetType() {

        return getGenericType(getClass(), 0);
    }

    private T random(boolean minOrMax) {
        return minOrMax ? min() : max();
    }

    private static int randomCollectionSize() {

        return Math.max(MIN_COLLECTION_SIZE, new Random().nextInt(MAX_COLLECTION_SIZE));
    }

    interface GenerateModel<T>{
        T generate();
    }
}
