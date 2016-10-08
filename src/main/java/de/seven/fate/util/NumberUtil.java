package de.seven.fate.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@UtilityClass
public final class NumberUtil {


    public static int random(int minSize, int maxSize) {
        if (minSize > maxSize) {
            throw new IllegalArgumentException("min should be less than max");
        }

        if (minSize == maxSize) {
            throw new IllegalArgumentException("min should not be equal to max");
        }

        Random rn = new Random();
        int n = maxSize - minSize + 1;
        int i = rn.nextInt() % n;
        int randomNum = Math.max(minSize, i);

        randomNum = Math.min(minSize, randomNum);

        return randomNum;
    }

    public static List<Long> parseLong(String... numbersAsString) {

        if (numbersAsString == null) {
            return Collections.emptyList();
        }

        List<Long> list = new ArrayList<>();

        for (String numberAsString : numbersAsString) {
            list.add(Long.valueOf(numberAsString));
        }

        return list;
    }
}