package de.telran;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DesignStructureTest {

    DesignStructure ds= new DesignStructure();
    Map<Long, Integer> design = new HashMap<>();
    ArrayList<Long> keynumber;

    @Test
    public void testePut_emptyObject_newElements() {
        design.put(3L, 1);
        design.put(2L, 3);
        design.put(2L, 3);
        design.put(1L, 4);
        design.put(1L, 4);
        assertEquals(3, design.size());
    }


    @Test
    public void testSizePut_emptyObject_noElements() {
        assertEquals(0, design.size());
    }

    @Test
    public void testSizePut_NotEmptyObject_notExistKey() {
        design.put(1L, 3);
        design.put(2L, 6);
        design.put(3L, 9);
        design.remove(3L);
        assertEquals(2, design.size());

    }

    @Test
    public void test_Put_and_Empty() {
        design.put(1L, 3);
        design.put(2L, 6);
        design.put(4L, 9);

        assertEquals(null, design.remove(3));

    }

    @Test
    public void testAdd_and_Remove() {

        assertEquals(false, ds.remove(9L));
        assertEquals(true, ds.add(12L));

    }

          /*
        @Test
        public void testAddGet_100RandomNumbers() {
            keynumber.add(20L);
            keynumber.add(10L);
            keynumber.add(-5L);
            keynumber.add(-5L);
            keynumber.add(105L);
            keynumber.add(55L);
            keynumber.add(-5L);
            keynumber.add(235L);
            keynumber.add(4575L);
            long[] randomNumbers = generateRandomNumbers(100);

            for (int i = 0; i < randomNumbers.length; i++) {
                keynumber.add(randomNumbers[i]);
            }

            assertListContents(randomNumbers);
        }

    protected void assertListContents(long[] array) {
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], ds.getRandom());
        }
    }

    private long[] generateRandomNumbers(int n) {
        long[] res = new long[n];
        Random rnd = new Random();

        for (int i = 0; i < n; i++) {
            res[i] = rnd.nextInt();
        }

        return res;
    }

*/
}
