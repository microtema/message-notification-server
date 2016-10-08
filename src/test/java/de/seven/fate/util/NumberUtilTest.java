package de.seven.fate.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 08.10.2016.
 */
public class NumberUtilTest {

    NumberUtil sut;

    @Test
    public void random() {

        int random = NumberUtil.random(0, 10);

        assertTrue(random >= 0);
        assertTrue(random <= 10);
    }

    @Test
    public void parseLong() {
        List<Long> longs = NumberUtil.parseLong("1", "2");

        assertArrayEquals(Arrays.asList(1l, 2l).toArray(), longs.toArray());
    }

    @Test
    public void parseLongOnEmptyVarArg() {
        List<Long> longs = NumberUtil.parseLong();

        assertTrue(longs.isEmpty());
    }

    @Test(expected = NumberFormatException.class)
    public void parseLongWillThrowNumberFormatException() {

        NumberUtil.parseLong("foo");
    }

}