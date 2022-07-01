package de.telran;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;



public class MinPointsInSegmentsTest {
    MinPointsInSegments minPointsInSegments = new MinPointsInSegments();

    @Test
    public void testGetMinPointsNumber_segments() {
        List<Segment> segments = Arrays.asList(
                new Segment(0.6, 0.9),
                new Segment(0.2, 0.7),
                new Segment(0.4, 0.8),
                new Segment(0.1, 0.5),
                new Segment(0, 0.3)
        );

        assertEquals(2, minPointsInSegments.getMinPointsNumber(segments));
    }

    @Test
    public void testGetMinPointsNumber_points() {
        List<Segment> segments = Arrays.asList(
                new Segment(0.6, 0.6),
                new Segment(0.2, 0.2),
                new Segment(0.4, 0.7),
                new Segment(0.1, 0.1),
                new Segment(0, 0)
        );

        assertEquals(4, minPointsInSegments.getMinPointsNumber(segments));
    }
    @Test
    public void testGetMinPointsNumber_points_2() {
        List<Segment> segments = Arrays.asList(
                new Segment(0.2, 0.6),
                new Segment(0.2, 0.6),
                new Segment(0.2, 0.6)

        );

        assertEquals(1, minPointsInSegments.getMinPointsNumber(segments));
    }

    @Test
    public void testGetMinPointsNumber_points_3() {
        List<Segment> segments = Arrays.asList(
                new Segment(0.2, 0.6),
                new Segment(0.7, 0.9),
                new Segment(1.0, 1.6)

        );

        assertEquals(3, minPointsInSegments.getMinPointsNumber(segments));
    }

}

