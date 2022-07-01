package de.telran;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class SortByBinaryTest {
    SortByBinary sortByBinary = new SortByBinary();


    @Test
     void sort_test_1() {
        Integer[] actual =new Integer[]{5, 8, 2, 4, 3, 128} ;
        Integer [] expected  = SortByBinary.sort(actual);

            assertEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test
    void sort_test_2() {
        Integer[] actual =new Integer[]{11, 10, 7, 23, 24, 11} ;
        Integer [] expected  = SortByBinary.sort(actual);

        assertEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test
    void sort_test_3() {
        Integer[] actual =new Integer[]{0, 1, 111, 222, 0, 333} ;
        Integer [] expected  = SortByBinary.sort(actual);

        assertEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }
}