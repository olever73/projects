package de.telran;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

    class PointServiceTest {

        PointService pst = new PointService();

        @Test
        public void test_collectPoints_emptyPointsEmptyCuts_empty_0() {
            List<Integer> points = Arrays.asList();
            List<Cut> cuts = Arrays.asList();
            Map<Integer, Integer> expected = new HashMap<>();
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void test_collectPoints_emptyPointsEmptyCuts_1() {
            List<Integer> points = Arrays.asList(1);
            List<Cut> cuts = Arrays.asList(new Cut(0, 2));
            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(1,1);
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void getCoveringCutsNumberByPoint_test_1() {
            List<Integer> points = Arrays.asList(1);
            List<Cut> cuts = Arrays.asList(new Cut(2, 4));
            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(1,0);
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void getCoveringCutsNumberByPoint_test_2() {
            List<Integer> points = Arrays.asList(3);
            List<Cut> cuts  = Arrays.asList(new Cut(2, 4), new Cut(0, 4),
                    new Cut(1, 3));
            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(3,3);
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void getCoveringCutsNumberByPoint_test_3() {
            List<Integer> points = Arrays.asList(3, 5, 7);
            List<Cut> cuts  = Arrays.asList(new Cut(1, 9));
            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(3,1);
            expected.put(5,1);
            expected.put(7,1);
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void getCoveringCutsNumberByPoint_test_4() {
            List<Integer> points = Arrays.asList(3, 5, 7);
            List<Cut> cuts = Arrays.asList(new Cut(1, 4));

            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(3,1);
            expected.put(5,0);
            expected.put(7,0);

            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }

        @Test
        public void getCoveringCutsNumberByPoint_test_5() {
            List<Integer> points = Arrays.asList(1, 2, 3, 4, 5);
            List<Cut> cuts  = Arrays.asList(
                    new Cut(0, 2),
                    new Cut(4, 5)
            );

            Map<Integer, Integer> expected = new HashMap<>();
            expected.put(1,1);
            expected.put(2,1);
            expected.put(3,0);
            expected.put(4,1);
            expected.put(5,1);
            assertEquals(expected, pst.getCoveringCutsNumberByPoint(points, cuts));
        }
    }