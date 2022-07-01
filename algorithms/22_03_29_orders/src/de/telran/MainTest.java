package de.telran;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main m =new Main();
    @Test
    public void test_countOrdersNumber1() {
        long[] array = {1000000, 1200000, 1250000, 1300000, 1600000, 1700000, 1800000, 1850000, 1890000};
        assertArrayEquals(new int[]{0, 1, 2, 3, 1, 1, 2, 3, 4}, m.countOrdersNumber(array, 5));
    }

}